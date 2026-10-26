package com.lhwdev.minecraft.railx.registry

import com.mojang.blaze3d.platform.InputConstants
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.neoforged.bus.api.EventPriority
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import org.lwjgl.glfw.GLFW

@OnlyIn(Dist.CLIENT)
enum class AllKeys(val description: String, val defaultKey: Int) {
	FlexiblePlacement(description = RailXLang.KeyFlexiblePlacement, defaultKey = GLFW.GLFW_KEY_LEFT_CONTROL),
	
	ThrottleAccelerate(description = RailXLang.KeyThrottleAccelerate, defaultKey = GLFW.GLFW_KEY_LEFT_BRACKET),
	ThrottleNeutral(description = RailXLang.KeyThrottleNeutral, defaultKey = GLFW.GLFW_KEY_SEMICOLON),
	ThrottleBrake(description = RailXLang.KeyThrottleBrake, defaultKey = GLFW.GLFW_KEY_PERIOD),
	ThrottleEmergencyBrake(description = RailXLang.KeyThrottleEmergencyBrake, defaultKey = GLFW.GLFW_KEY_COMMA),
	ThrottleReverserForward(description = RailXLang.KeyThrottleReverserForward, defaultKey = GLFW.GLFW_KEY_O),
	ThrottleReverserBackward(description = RailXLang.KeyThrottleReverserBackward, defaultKey = GLFW.GLFW_KEY_K),
	;
	
	var bound: KeyMapping? = null
		private set
	
	val key: Int
		get() = bound?.key?.value ?: defaultKey
	
	var isPressed: Boolean = false
		private set
	
	var isKeyDown: Boolean = false
		private set
	
	var isKeyUp: Boolean = false
		private set
	
	var pressedTicks: Int = 0
		private set
	
	private fun updatePressed() {
		val previous = isPressed
		val current = bound?.isDown ?: isKeyDown(defaultKey)
		isPressed = current
		
		isKeyDown = !previous && current
		isKeyUp = previous && !current
		
		if(isKeyDown) pressedTicks = 0
		else if(current) pressedTicks++
	}
	
	
	@EventBusSubscriber(Dist.CLIENT)
	companion object {
		@SubscribeEvent
		fun register(event: RegisterKeyMappingsEvent) {
			for(key in entries) {
				val mapping = KeyMapping(key.description, key.defaultKey, RailXLang.KeyCategories)
				key.bound = mapping
				event.register(mapping)
			}
		}
		
		@SubscribeEvent(priority = EventPriority.HIGHEST)
		private fun onTick(event: ClientTickEvent.Pre) {
			for(key in AllKeys.entries) {
				key.updatePressed()
			}
		}
		
		fun isKeyDown(key: Int): Boolean =
			InputConstants.isKeyDown(Minecraft.getInstance().window.window, key)
	}
}
