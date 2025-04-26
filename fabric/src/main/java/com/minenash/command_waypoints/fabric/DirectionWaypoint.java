package com.minenash.command_waypoints.fabric;

import com.mojang.datafixers.util.Either;
import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.waypoints.TrackedWaypoint;
import net.minecraft.world.waypoints.WaypointStyleAssets;

import java.util.Optional;

public class DirectionWaypoint extends TrackedWaypoint {

    public static final DirectionWaypoint[] DIRECTIONS = new DirectionWaypoint[]{
        new DirectionWaypoint("north", -180),
        new DirectionWaypoint("corner", -135),
        new DirectionWaypoint("east", -90),
        new DirectionWaypoint("corner", -45),
        new DirectionWaypoint("south", 0),
        new DirectionWaypoint("corner", 45),
        new DirectionWaypoint("west", 90),
        new DirectionWaypoint("corner", 135)
    };


    public final int yaw;
    public DirectionWaypoint(String direction, int yaw) {
        super(Either.right(direction), createIcon(direction), TrackedWaypoint.Type.EMPTY);
        this.yaw = yaw;
    }

    public static Icon createIcon(String direction) {
        Icon icon = new Icon();
        icon.style = ResourceKey.create(WaypointStyleAssets.ROOT_ID, ResourceLocation.tryBuild("direction_waypoints", direction));
        icon.color = Optional.of( 0xFFFFFFFF );
        return icon;
    }

    @Override
    public void update(TrackedWaypoint trackedWaypoint) {}

    @Override
    public void writeContents(ByteBuf byteBuf) {}

    @Override
    public double yawAngleToCamera(Level level, Camera camera) {
        return Mth.degreesDifference(camera.yaw(), yaw);
    }

    @Override
    public int pitchDirectionToCamera(Level level, Projector projector) {
        return 0;
    }

    @Override
    public double distanceSquared(Entity entity) {
        return 100;
    }
}
