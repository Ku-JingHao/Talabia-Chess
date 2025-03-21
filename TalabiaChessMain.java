public class TalabiaChessMain {
    public static void main(String[] args) {
        TalabiaChessView view = new TalabiaChessView();
        TalabiaChessModel model = new TalabiaChessModel();
        TalabiaChessController controller = new TalabiaChessController(model, view);
    }
}