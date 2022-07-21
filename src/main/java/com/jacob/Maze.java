package com.jacob;

import dev.dewy.nbt.Nbt;
import dev.dewy.nbt.api.Tag;
import dev.dewy.nbt.api.registry.TagTypeRegistry;
import dev.dewy.nbt.io.CompressionType;
import dev.dewy.nbt.tags.array.ByteArrayTag;
import dev.dewy.nbt.tags.array.IntArrayTag;
import dev.dewy.nbt.tags.array.LongArrayTag;
import dev.dewy.nbt.tags.collection.CompoundTag;
import dev.dewy.nbt.tags.collection.ListTag;
import dev.dewy.nbt.tags.primitive.*;
import dev.dewy.nbt.tags.*;

import java.io.File;
import java.io.IOException;

public class Maze {
    private final Nbt nbt = new Nbt();
    private int[] size = new int[3];
    private boolean[][][] mazeCells;
    private int blockCount = 0;
    private int paletteCount = 0;

    [0,0,0,0,0],
    []
    public Maze(int width, int height, int depth){
        this.size = new int[]{width, height, depth};
        mazeCells = new boolean[width * 2 + 1][height * 2 + 1][depth * 2 + 1];
    }

    public void load(String seed){
        System.out.println("File has been \"loaded\"");
    }
    public void loadFromFile(String fileName){
        System.out.println("File has been \"loaded\"");
    }
    public void printTextFile(String fileName) {
        System.out.println("File has been \"loaded\"");
    }
    public void printNbtFile(String fileName){
        CompoundTag root = new CompoundTag("");

        root.put(new IntTag("DataVersion", 2975));

        // blocks
        ListTag<CompoundTag> blocksTag = new ListTag<>("blocks");
        blocksTag.add(getBlocksEntry(0,0,0,0));
        blocksTag.add(getBlocksEntry(0,0,1,0));
        blocksTag.add(getBlocksEntry(0,0,2,0));
        blocksTag.add(getBlocksEntry(0,0,3,0));
        blocksTag.add(getBlocksEntry(1,0,0,0));
        blocksTag.add(getBlocksEntry(1,0,1,0));
        blocksTag.add(getBlocksEntry(1,0,3,0));
        blocksTag.add(getBlocksEntry(2,0,0,0));
        blocksTag.add(getBlocksEntry(2,0,1,0));
        blocksTag.add(getBlocksEntry(2,0,2,0));
        blocksTag.add(getBlocksEntry(2,0,3,0));
        root.put(blocksTag);

        // entities
        ListTag<?> entitiesTag = new ListTag<>("entities");
        root.put(entitiesTag);

        // palette
        ListTag<CompoundTag> paletteTag = new ListTag<>("palette");
        paletteTag.add(getPaletteEntry("stone_bricks"));
//        paletteTag.add(getPaletteEntry("cobblestone"));
//        paletteTag.add(getPaletteEntry("chiseled_stone_bricks"));
//        paletteTag.add(getPaletteEntry("stone_brick_stairs", "nb-f"));
        root.put(paletteTag);

        //size
        ListTag<IntTag> sizeTag = new ListTag<>("size");
        sizeTag.add(new IntTag("0", 3));
        sizeTag.add(new IntTag("1", 2));
        sizeTag.add(new IntTag("2", 4));
        root.put(sizeTag);

        try {
            nbt.toFile(root, new File(fileName), CompressionType.NONE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("DONE");
    }
    public CompoundTag getPaletteEntry(String name, String blockProperties) {
        CompoundTag paletteEntry = new CompoundTag(String.valueOf(paletteCount++));


        paletteEntry.put(new StringTag("Name", "minecraft:"+name));

        if(!"none".equals(blockProperties)) {
            String[] p = parseProperties(blockProperties);

            CompoundTag properties = new CompoundTag("Properties");
            properties.put(new StringTag("facing",      p[0]));
            properties.put(new StringTag("half",        p[1]));
            properties.put(new StringTag("shape",       p[2]));
            properties.put(new StringTag("waterlogged", p[3]));

            paletteEntry.put(properties);
        }

        return paletteEntry;
    }
    public CompoundTag getPaletteEntry(String name) {
        return getPaletteEntry(name, "none");
    }
    public String[] parseProperties(String properties) {
        //todo: use string utils
        String[] rtrn = properties.split("",4);

        rtrn[0] = rtrn[0].replace("n","north")
                         .replace("e","east")
                         .replace("s","south")
                         .replace("w","west");

        rtrn[1] = rtrn[1].replace("t","top")
                         .replace("b","bottom");

        rtrn[2] = rtrn[2].replace("-","straight")
                         .replace("i","inner")
                         .replace("o","outer");

        rtrn[3] = rtrn[3].replace("l","true")
                         .replace("f","false");

        return rtrn;
    }

    public CompoundTag getBlocksEntry(int relX, int relY, int relZ, int blockId){
        CompoundTag block = new CompoundTag(String.valueOf(blockCount++));

        // pos coordinates
        ListTag<IntTag> sizeTag = new ListTag<>("pos");
        sizeTag.add(new IntTag("0", relX));
        sizeTag.add(new IntTag("1", relY));
        sizeTag.add(new IntTag("2", relZ));
        block.put(sizeTag);

        // block id
        block.put(new IntTag("state", blockId));

        return block;
    }

    // prints one cell of the maze to the blockTag, whose type is specified by sectionType
    public void sectionLayer(CompoundTag blockTag, int offsetX, int offsetY, int offsetZ, String sectionType){

    }
}
