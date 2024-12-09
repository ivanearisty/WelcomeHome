package PDS.Project3.Service;

import PDS.Project3.Domain.Entities.Piece;
import PDS.Project3.Repository.PieceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PieceServiceImpl implements PieceService {

    private final PieceRepository<Piece> pieceRepository;

    @Override
    public List<Piece> getAllPiecesForItem(String itemId) {
        return pieceRepository.findAll(itemId);
    }

    @Override
    public Piece addPieceToItem(Piece piece, String itemId) {
        return pieceRepository.set(piece, itemId);
    }

    @Override
    public Boolean deletePiece(Piece piece) {
        return pieceRepository.delete(piece);
    }
}
