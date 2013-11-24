package com.savanto.correspondencechess;


/**
 * The backend representation of the game board, holding all of the pieces
 * and their positions.
 */
public class Board
{
	/* The game pieces, stored as bits in long variables */
	private long blackPawns, 	whitePawns;
	private long blackBishops, 	whiteBishops;
	private long blackKnights, 	whiteKnights;
	private long blackRooks, 	whiteRooks;
	private long blackQueens, 	whiteQueens;
	private long blackKings, 	whiteKings;

	/* The game pieces as objects on the board */
	protected Piece[] pieces = new Piece[64];

	/* The current turn */
	protected Color turn;

	/**
	 * Constructs a standard new game board.
	 * Black pieces capitalized, white lowercase
	 *    A B C D E F G H
	 *  +-----------------+
	 * 8| R N B Q K B N R |8
	 * 7| P P P P P P P P |7
	 * 6| . . . . . . . . |6
	 * 5| . . . . . . . . |5
	 * 4| . . . . . . . . |4
	 * 3| . . . . . . . . |3
	 * 2| p p p p p p p p |2
	 * 1| r n b q k b n r |1
	 *  +-----------------+
	 *    A B C D E F G H
	 *
	 * Origin is in the top left corner at A8
	 */
	public Board()
	{
		this(BLACKPAWNS, WHITEPAWNS, BLACKBISHOPS, WHITEBISHOPS,
				BLACKKNIGHTS, WHITEKNIGHTS, BLACKROOKS, WHITEROOKS,
				BLACKQUEENS, WHITEQUEENS, BLACKKINGS, WHITEKINGS, Color.WHITE);
	}

	/**
	 * Construct a board from the given piece information
	 * @param blackPawns
	 * @param whitePawns
	 * @param blackBishops
	 * @param whiteBishops
	 * @param blackKnights
	 * @param whiteKnights
	 * @param blackRooks
	 * @param whiteRooks
	 * @param blackQueens
	 * @param whiteQueens
	 * @param blackKings
	 * @param whiteKings
	 * @param turn
	 */
	public Board(long blackPawns,	long whitePawns,
				 long blackBishops,	long whiteBishops,
				 long blackKnights,	long whiteKnights,
				 long blackRooks,	long whiteRooks,
				 long blackQueens,	long whiteQueens,
				 long blackKings,	long whiteKings,
				 Color turn)
	{
		this.blackPawns		= blackPawns;
		this.whitePawns		= whitePawns;
		this.blackBishops	= blackBishops;
		this.whiteBishops	= whiteBishops;
		this.blackKnights	= blackKnights;
		this.whiteKnights	= whiteKnights;
		this.blackRooks		= blackRooks;
		this.whiteRooks		= whiteRooks;
		this.blackQueens	= blackQueens;
		this.whiteQueens	= whiteQueens;
		this.blackKings		= blackKings;
		this.whiteKings		= whiteKings;

		this.turn = turn;

		// Construct the array representing the Pieces on the Board.
		for (int i = 0; i < 64; ++i)
		{
			long bit = 1L << i;
			// If bit is set at given location for each of the piece types,
			// create a Piece there
			// Piece(Type, Color, rank, file)
			if ((bit & blackPawns) != 0)
				pieces[i] = new Pawn(Color.BLACK, i);
			else if ((bit & whitePawns) != 0)
				pieces[i] = new Pawn(Color.WHITE, i);

//			else if ((bit & blackBishops) != 0)
//				pieces[i] = new Bishop(Color.BLACK, i);
//			else if ((bit & whiteBishops) != 0)
//				pieces[i] = new Bishop(Color.WHITE, i);
//
//			else if ((bit & blackKnights) != 0)
//				pieces[i] = new Knight(Color.BLACK, i);
//			else if ((bit & whiteKnights) != 0)
//				pieces[i] = new Knight(Color.WHITE, i);
//
//			else if ((bit & blackRooks) != 0)
//				pieces[i] = new Rook(Color.BLACK, i);
//			else if ((bit & whiteRooks) != 0)
//				pieces[i] = new Rook(Color.WHITE, i);
//
//			else if ((bit & blackQueens) != 0)
//				pieces[i] = new Queen(Color.BLACK, i);
//			else if ((bit & whiteQueens) != 0)
//				pieces[i] = new Queen(Color.WHITE, i);
//
//			else if ((bit & blackKings) != 0)
//				pieces[i] = new King(Color.BLACK, i);
//			else if ((bit & whiteKings) != 0)
//				pieces[i] = new King(Color.WHITE, i);
		}
	}

	/* Basic 1-square position calculations on the Board */
	protected static int N(int position) { return position - 8; };
	protected static int S(int position) { return position + 8; };
	protected static int E(int position) { return position + 1; };
	protected static int W(int position) { return position - 1; };

	/* Tests for the edges of the Board */
	protected static boolean leftEdge(int position) { return position % 8 == 0; }
	protected static boolean rightEdge(int position) { return position % 8 == 7; }
	protected static boolean topEdge(int position) { return position < 8; }
	protected static boolean bottomEdge(int position) { return position > 55; }

	/* Coordinates to position conversions */
	public static Coordinates getCoordinates(int position) { return new Coordinates(position % 8, position / 8); }




//	public Piece getPiece(int i) { return pieces[i]; }


	/* Initial game piece positions */
	// Pawns
	private static final long BLACKPAWNS	= 0x000000000000FF00L;
	private static final long WHITEPAWNS	= 0x00FF000000000000L;
	// Bishops
	private static final long BLACKBISHOPS	= 0x0000000000000024L;
	private static final long WHITEBISHOPS	= 0x2400000000000000L;
	// Knights
	private static final long BLACKKNIGHTS	= 0x0000000000000042L;
	private static final long WHITEKNIGHTS	= 0x4200000000000000L;
	// Rooks
	private static final long BLACKROOKS	= 0x0000000000000081L;
	private static final long WHITEROOKS	= 0x8100000000000000L;
	// Queens
	private static final long BLACKQUEENS	= 0x0000000000000008L;
	private static final long WHITEQUEENS	= 0x0800000000000000L;
	// Kings
	private static final long BLACKKINGS	= 0x0000000000000010L;
	private static final long WHITEKINGS	= 0x1000000000000000L;
}
