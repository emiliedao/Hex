package hex;

public class Main {

	private final static int SIZE = 7;
	
	public static void main(String[] args) {
		HexModel model = new HexModel(SIZE);
		HexController controller = new HexController(model);
		GridView grid = new GridView(model, controller, SIZE);
		
		model.addObserver(grid);
//		model.displayConsoleGrid();

	}

}
