package com.example.crew.config;

import com.example.crew.repo.TripDetailsRepo;
import com.example.crew.repo.triphistoryrepo;


public final class StaticUtils {
	public static TripDetailsRepo tripdetailsrepo;
	public static triphistoryrepo triphistory;

	public static void setMyConfig(TripDetailsRepo myConfig, triphistoryrepo triphistory) {
		StaticUtils.tripdetailsrepo = myConfig;
		StaticUtils.triphistory = triphistory;
	}

	private StaticUtils() {
	}
}