package com.jacob;

public enum BlockType {
    STONE_BRICKS("minecraft:stone_bricks"),
    STONE_BRICK_STAIRS("minecraft:stone_brick_stairs","nb-f");
    private String blockName;
    private String properties;

    BlockType(String blockName) {
        this.blockName = blockName;
        this.properties = "none";
    }
    BlockType(String blockName, String properties) {
        this.blockName = blockName;
        this.properties = properties;
    }

    public String getBlockName() {
        return blockName;
    }
}
