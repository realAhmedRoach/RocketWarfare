package dev.thetechnokid.rw.utils;

import java.util.ArrayList;

import dev.thetechnokid.rw.entities.RocketPart;

public class PartList extends ArrayList<RocketPart> {
	private static final long serialVersionUID = 213829L;

	
	@Override
	public RocketPart get(int index) {
		return new RocketPart(super.get(index));
	}
}
