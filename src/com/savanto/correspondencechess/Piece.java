package com.savanto.correspondencechess;


/**
 * A chess piece in the game.
 */
public abstract class Piece
{
	protected Color color;
	protected Type type;
	protected long movement;


	protected Piece(Color color, Type type, int position)
	{
		this.color = color;
		this.type = type;
		this.movement = 0;
	}

	/**
	 * Calculates the available movement of the Piece,
	 * according to the specific movement rules of the Piece.
	 * @param position
	 */
	protected abstract void calculateMovement(int position);

//	public long getMovement() { return movement; }













//	public int type;
//	public int color;
//	public int rank;
//	public int file;

//	public Piece(int type, int color, int rank, int file)
//	{
//		this.type = type;
//		this.color = color;
//		this.rank = rank;
//		this.file = file;
//	}

//	public static final int BLACK 	= 0x0;
//	public static final int WHITE 	= 0x1;
//
//	public static final int PAWN	= 0x2;
//	public static final int BISHOP	= 0x4;
//	public static final int KNIGHT	= 0x8;
//	public static final int ROOK	= 0x10;
//	public static final int QUEEN	= 0x20;
//	public static final int KING	= 0x40;
}

enum Color	{ BLACK, WHITE };
enum Type	{ PAWN, BISHOP, KNIGHT, ROOK, QUEEN, KING };
