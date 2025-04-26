package com.minenash.command_waypoints.fabric.mixin;

import com.minenash.command_waypoints.fabric.DirectionWaypoint;
import net.minecraft.client.waypoints.ClientWaypointManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.waypoints.TrackedWaypoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ClientWaypointManager.class)
public class ClientWaypointManagerMixin {

    @Inject(method = "forEachWaypoint", at = @At(value = "HEAD"))
    private void addDirections(Entity entity, Consumer<TrackedWaypoint> consumer, CallbackInfo ci) {
        for (var point : DirectionWaypoint.DIRECTIONS)
            consumer.accept(point);
    }

}
