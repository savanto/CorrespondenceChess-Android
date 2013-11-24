package com.savanto.correspondencechess;

public class Pawn extends Piece
{
	private boolean moved;

	protected Pawn(Color color, int position)
	{
		super(color, Type.PAWN, position);
		moved = false;
		calculateMovement(position);
	}

    /**
     * Pawn movement: forward one square, capture diagonally.
     * Special: first move forward two squares, en passant.
     *
     * . . . . . . . .
     * . . . . . . . .
     * . . . . . . . .
     * . . . . . . . .
     * 1 . . . 1 . . .
     * m c . c m c . .
     * p . . . p . . .
     * . . . . . . . .
     * m - standard move
     * 1 - first move
     * c - capture
     * en passant - special capture at the midline of the board.
     */
	@Override
	protected void calculateMovement(int position)
	{
		// Standard move: 1 square forward
		movement |= 1 << (color == Color.WHITE ? Board.N(position) : Board.S(position));

		// First move: 2 squares forward
		if (! moved)
			movement |= 1 << (color == Color.WHITE ? Board.N(Board.N(position)) : Board.S(Board.S(position)));

		// Capture
		if (Board.leftEdge(position))
			movement |= 1 << (color == Color.WHITE ? Board.N(Board.E(position)) : Board.S(Board.E(position)));
		else if (Board.rightEdge(position))
			movement |= 1 << (color == Color.WHITE ? Board.N(Board.W(position)) : Board.S(Board.W(position)));
		else
		{
			movement |= 1 << (color == Color.WHITE ? Board.N(Board.E(position)) : Board.S(Board.E(position)));
			movement |= 1 << (color == Color.WHITE ? Board.N(Board.W(position)) : Board.S(Board.W(position)));
		}
	}

}
