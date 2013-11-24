package com.savanto.correspondencechess;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;

/**
 * Activity containing the main graphics of the application --
 * the game board on which the player makes his move, the chess pieces, etc.
 */
public class BoardActivity extends Activity
{
	private Board board;

	/* User interface elements */
	private RelativeLayout relativeLayout;
	private SquareLayout squareLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// Inflate the main board layout
		this.setContentView(R.layout.board);

		///////////////////////////
		// TODO
		// Initialize the correct game state, ie. new game, saved game, etc.
		// For now, just a new one
		///////////////////////////

		// Initialize new game Board
		board = new Board();

		// Initialize graphical board from the backend Board.
		// This can only be done after the layout has been measured
		// and laid out, so we do this from a global layout listener.
		relativeLayout = (RelativeLayout) findViewById(R.id.layout);
		squareLayout = (SquareLayout) findViewById(R.id.board);
		final ViewTreeObserver vto = squareLayout.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener()
		{
			@SuppressWarnings("deprecation")
			@Override
			public void onGlobalLayout()
			{
				// Create the graphical pieces from the backend Board.
				int pieceSize = squareLayout.getChildSize();
				for (int i = 0; i < 64; ++i)
				{
					if (board.pieces[i] == null)
						continue;
					int resid = R.drawable.ic_launcher;

					switch (board.pieces[i].color)
					{
						case WHITE:
							switch (board.pieces[i].type)
							{
								case PAWN: resid = R.drawable.white_pawn;
									break;
								case BISHOP: resid = R.drawable.white_bishop;
									break;
								case KNIGHT: resid = R.drawable.white_knight;
									break;
								case ROOK: resid = R.drawable.white_rook;
									break;
								case QUEEN: resid = R.drawable.white_queen;
									break;
								case KING: resid = R.drawable.white_king;
									break;
							}
							break;

						case BLACK:
							switch (board.pieces[i].type)
							{
								case PAWN: resid = R.drawable.black_pawn;
									break;
								case BISHOP: resid = R.drawable.black_bishop;
									break;
								case KNIGHT: resid = R.drawable.black_knight;
									break;
								case ROOK: resid = R.drawable.black_rook;
									break;
								case QUEEN: resid = R.drawable.black_queen;
									break;
								case KING: resid = R.drawable.black_king;
									break;
							}
							break;
					}
					relativeLayout.addView(new PieceView(BoardActivity.this, resid, pieceSize, Board.getCoordinates(i)));
				}
				// Done setting up game board; OnGlobalLayoutListener is no longer needed, so remove it.
				squareLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});
	}
}
