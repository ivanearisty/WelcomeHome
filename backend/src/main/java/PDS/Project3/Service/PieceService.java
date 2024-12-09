package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Piece;

import java.util.List;

public interface PieceService {
    List<Piece> getAllPiecesForItem(String itemId);
    Piece addPieceToItem(Piece piece, String itemId);
    Boolean deletePiece(Piece piece);
}
