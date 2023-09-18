package io.github.thatsmusic99.resurgencelib.api;

import com.google.common.base.Preconditions;
import io.github.thatsmusic99.resurgencelib.utilities.MinMaxValue;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * The Boundary class represents a three-dimensional region defined by minimum and maximum coordinates
 * in a specific Minecraft world. It is often used to define areas or volumes within the game world.
 */
public class Boundary {

    // Store a weak reference to the world to avoid memory leaks
    @NotNull private final WeakReference<World> worldReference;

    // Store the minimum and maximum values for x, y, and z coordinates of the boundary
    @NotNull private final MinMaxValue<Integer> x, y, z;

    /**
     * Constructs a Boundary object from two location points. The locations must be in the same world.
     *
     * @param location1 The first location point.
     * @param location2 The second location point.
     * @throws IllegalArgumentException if the locations are not in the same world.
     */
    public Boundary(@NotNull Location location1, @NotNull Location location2) {
        // Check if both locations are in the same world
        Preconditions.checkArgument(location1.getWorld().equals(location2.getWorld()), "Both locations need to be from the same world", location1, location2);

        this.worldReference = new WeakReference<>(location1.getWorld());

        // Initialize the minimum and maximum values for x, y, and z coordinates
        this.x = new MinMaxValue<>(location1.getBlockX(), location2.getBlockX());
        this.y = new MinMaxValue<>(location1.getBlockY(), location2.getBlockY());
        this.z = new MinMaxValue<>(location1.getBlockZ(), location2.getBlockZ());
    }

    /**
     * Constructs a Boundary object with specific coordinates in a given world.
     *
     * @param world The world in which the boundary exists.
     * @param x1 The minimum X-coordinate.
     * @param y1 The minimum Y-coordinate.
     * @param z1 The minimum Z-coordinate.
     * @param x2 The maximum X-coordinate.
     * @param y2 The maximum Y-coordinate.
     * @param z2 The maximum Z-coordinate.
     */
    public Boundary(@NotNull World world, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.worldReference = new WeakReference<>(world);

        // Initialize the minimum and maximum values for x, y, and z coordinates
        this.x = new MinMaxValue<>(x1, x2);
        this.y = new MinMaxValue<>(y1, y2);
        this.z = new MinMaxValue<>(z1, z2);
    }

    /**
     * Retrieves the world in which this boundary exists.
     *
     * @return The World object or null if the world is not found.
     */
    public @Nullable World getWorld() {
        return worldReference.get();
    }

    /**
     * Retrieves the lower corner location of the boundary.
     *
     * @return The lower corner Location of the boundary.
     * @throws NullPointerException if the world is not found.
     */
    public Location getLowerCorner() {
        World world = getWorld();
        Preconditions.checkNotNull(world, "World not found, null was provided");

        return new Location(world, x.minimum(), y.minimum(), z.minimum());
    }

    /**
     * Retrieves the upper corner location of the boundary.
     *
     * @return The upper corner Location of the boundary.
     * @throws NullPointerException if the world is not found.
     */
    public Location getUpperCorner() {
        World world = getWorld();
        Preconditions.checkNotNull(world, "World not found, null was provided");

        return new Location(world, x.maximum(), y.maximum(), z.maximum());
    }

    /**
     * Retrieves a list of Chunks that intersect with the boundary.
     *
     * @return A List of Chunks intersecting with the boundary.
     */
    public List<Chunk> getChunks() {
        List<Chunk> chunkList = new ArrayList<>();
        World world = getWorld();

        if (world == null) return chunkList;

        int xMin = this.x.minimum() & ~0xf;
        int xMax = this.x.maximum() & ~0xf;
        int zMin = this.z.minimum() & ~0xf;
        int zMax = this.z.maximum() & ~0xf;

        for (int x = xMin; x <= xMax; x += 16) {
            for (int z = zMin; z <= zMax; z += 16) {
                chunkList.add(world.getChunkAt(x >> 4, z >> 4));
            }
        }
        return chunkList;
    }

    /**
     * Gets the block at the specified relative coordinates within the boundary.
     *
     * @param x The relative X-coordinate.
     * @param y The relative Y-coordinate.
     * @param z The relative Z-coordinate.
     * @return The Block at the specified relative coordinates.
     * @throws NullPointerException if the world is not found.
     */
    public Block getRelativeBlock(int x, int y, int z) {
        World world = getWorld();
        Preconditions.checkNotNull(world, "World not found, null was provided");

        return world.getBlockAt(
                this.x.minimum() + x,
                this.y.minimum() + y,
                this.z.minimum() + z);
    }

    /**
     * Checks if the specified coordinates are within the boundary.
     *
     * @param x The X-coordinate to check.
     * @param y The Y-coordinate to check.
     * @param z The Z-coordinate to check.
     * @return true if the coordinates are within the boundary, false otherwise.
     */
    public boolean contains(int x, int y, int z) {
        return x >= this.x.minimum() && x <= this.x.maximum()
                && y >= this.y.minimum() && y <= this.y.maximum()
                && z >= this.z.minimum() && z <= this.z.maximum();
    }

    /**
     * Retrieves the center location within the boundary.
     *
     * @return The center Location within the boundary.
     * @throws NullPointerException if the world is not found.
     */
    public Location getCenter() {
        World world = getWorld();
        Preconditions.checkNotNull(world, "World not found, null was provided");

        int xMax = this.x.maximum() + 1;
        int yMax = this.y.maximum() + 1;
        int zMax = this.z.maximum() + 1;
        return new Location(world,
                this.x.minimum() + (xMax - this.x.minimum()) / 2.0,
                this.y.minimum() + (yMax - this.y.minimum()) / 2.0,
                this.z.minimum() + (zMax - this.z.minimum()) / 2.0
        );
    }

    /**
     * Retrieves the size of the boundary in the X-axis.
     *
     * @return The size of the boundary in the X-axis.
     */
    public int getSizeX() {
        return (this.x.maximum() - this.x.minimum()) + 1;
    }

    /**
     * Retrieves the size of the boundary in the Y-axis.
     *
     * @return The size of the boundary in the Y-axis.
     */
    public int getSizeY() {
        return (this.y.maximum() - this.y.minimum()) + 1;
    }

    /**
     * Retrieves the size of the boundary in the Z-axis.
     *
     * @return The size of the boundary in the Z-axis.
     */
    public int getSizeZ() {
        return (this.z.maximum() - this.z.minimum()) + 1;
    }

    /**
     * Retrieves the volume of the boundary.
     *
     * @return The volume of the boundary, which is the product of its dimensions.
     */
    public int getVolume() {
        return this.getSizeX() * this.getSizeY() * this.getSizeZ();
    }
}

