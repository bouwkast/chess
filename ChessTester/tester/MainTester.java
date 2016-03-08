/**
 * 
 */
package tester;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import chess.objects.Piece;

@RunWith(Suite.class)
 @Suite.SuiteClasses({
     
     BishopTester.class,
     BoardTester.class,
     CastlingMoveTester.class,
     CastlingTester.class,
     CellTester.class,
     CheckmateTesting.class,
     ChessTester.class,
     EnPassantMoveTester.class,
     KingTester.class,
     KnightTester.class,
     MoveTester.class,
     PawnTester.class,
     PieceTester.class,
     QueenTester.class,
     RookTester.class
 })

public class MainTester {

}
