package com.savanto.correspondencechess;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Extension of the View class to allow easy creation and manipulation
 * of views containing chess pieces.
 */
public class PieceView extends View
{
	public PieceView(Context context)
	{
		super(context);
	}

	/**
	 * Produces graphic chess PieceView from given Piece.
	 * @param context
	 * @param resid - the resource id of the Drawable to use for this PieceView.
	 * @param size - the size to make the graphical PieceView.
	 * @param coords - rank and file Coordinates of the piece on the board.
	 */
	public PieceView(Context context, int resid, final int size, final Coordinates coords)
	{
		super(context);

		// Set background to Drawable
		setBackgroundResource(resid);

		// Set position
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(size, size);
		lp.leftMargin = coords.x * size;
		lp.topMargin = coords.y * size;
		setLayoutParams(lp);

		// OnTouchListener listens for user interaction with the PieceView
		setOnTouchListener(new OnTouchListener()
		{
			// Initial position of the PieceView, prior to move.
			private int leftMargin, topMargin;

			@Override
			public boolean onTouch(View view, MotionEvent event)
			{
				// Get the PieceView being touched, and its LayoutParams for position updating.
				PieceView pieceView = (PieceView) view;
				RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) pieceView.getLayoutParams();

				// Which touch event is occuring
				switch (event.getAction())
				{
					// The touch has just started
					case MotionEvent.ACTION_DOWN:
						// PieceView has been touched. Bring it
						// in front of all other views.
						pieceView.bringToFront();
						// Get the PieceView's parent and ask it to redraw itself.
						RelativeLayout parent = (RelativeLayout) pieceView.getParent();
						parent.requestLayout();
						parent.invalidate();

						// TODO
						// Record PieceView's initial coordinates
						leftMargin = lp.leftMargin;
						topMargin = lp.topMargin;
						break;

					// The touch event continues
					case MotionEvent.ACTION_MOVE:
						// The PieceView is being moved. Update
						// its raw coordinates.
						lp.leftMargin = (int) event.getRawX() - size / 2;
						lp.topMargin = (int) event.getRawY() - size * 2;
						pieceView.setLayoutParams(lp);

						break;

					// The touch event ends
					case MotionEvent.ACTION_UP:
						// The PieceView is released. Calculate its
						// final position and set it down squarely.
						// TODO check for legality, captures, special moves (en passant, castle, promotion)
						if (false)
						{
							lp.leftMargin = leftMargin;
							lp.topMargin = topMargin;
						}
						else
						{
							// TODO record completed moves, allow step-by-step undo
							lp.leftMargin = (int) event.getRawX() / size * size;
							lp.topMargin = ((int) event.getRawY() / size - 1) * size;
						}
						pieceView.setLayoutParams(lp);

						break;
				}
				return true;
			}
		});
	}
}
