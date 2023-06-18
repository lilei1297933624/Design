package com.lyx.batch.builder;

public interface TerrainBuiler {
    TerrainBuiler buildWall();
    TerrainBuiler buildFort();
    TerrainBuiler buildMine();
    Terrain build();
}
