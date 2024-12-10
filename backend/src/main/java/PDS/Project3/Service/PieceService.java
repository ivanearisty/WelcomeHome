package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Piece;

import java.util.List;

public interface PieceService {
    List<Piece> getAllPiecesForItem(String itemId);
    Piece addPiece(Piece piece);
    Boolean deletePiece(Piece piece);
    Boolean validateLocation(int roomNum, int shelfNum);
}
