package com.lyx.batch.builder;


public class ComplexTerrainBuilder implements TerrainBuiler {
    Terrain terrain = new Terrain();

    @Override
    public TerrainBuiler buildWall() {
        terrain.wall = new Wall(10,10,50,50);
        return this;
    }

    @Override
    public TerrainBuiler buildFort() {
        terrain.f = new Fort(10,10,50,50);
        return this;
    }

    @Override
    public TerrainBuiler buildMine() {
        terrain.m = new Mine(10, 10 , 50,50);
        return this;
    }

    @Override
    public Terrain build() {
        return terrain;
    }
}
