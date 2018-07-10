package com.kms.mobilemousepad.program_view;

public enum FrameLocationType {
	NORTH(0,-1), NORTH_EAST(1, -1), NORTH_WEST(-1, -1),
	SOUTH(0, 1), SOUTH_EAST(1, 1), SOUTH_WEST(-1, 1),
	EAST(1, 0), WEST(-1, 0), CENTER(0, 0);
	
	public final int x, y;
	
	private FrameLocationType(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
