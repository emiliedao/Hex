package hex;

import java.util.Observer;

public abstract class HexView implements Observer {
	
	HexModel model;
	HexController controller;
	
	public HexView(HexModel model, HexController controller) {
		this.model = model;
		this.controller = controller;
	}
	
}
