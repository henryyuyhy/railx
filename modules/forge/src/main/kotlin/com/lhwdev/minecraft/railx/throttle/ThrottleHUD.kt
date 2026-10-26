package com.lhwdev.minecraft.railx.throttle

import com.lhwdev.minecraft.railx.RailXConfig
import com.lhwdev.minecraft.railx.registry.RailXLang
import com.lhwdev.minecraft.railx.utils.orFalse
import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.LayeredDraw
import net.minecraft.network.chat.Component
import net.minecraft.util.Mth
import net.minecraft.world.level.GameType
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import kotlin.math.abs


@OnlyIn(Dist.CLIENT)
object ThrottleHUD : LayeredDraw.Layer {
	override fun render(graphics: GuiGraphics, deltaTracker: DeltaTracker) {
		val mc = Minecraft.getInstance()
		if(mc.options.hideGui || mc.gameMode?.playerMode == GameType.SPECTATOR) return
		
		val entity = ControlsHandler.getContraption()
		if(entity !is CarriageContraptionEntity) return
		if(!RailXConfig.Server.throttle.enabled.orFalse) return
		
		val carriage = entity.carriage ?: return
		if(mc.cameraEntity == null) return
		if(ControlsHandler.getControlsPos() == null) return
		
		val throttle = ThrottlesClient.throttle ?: return
		val text = Component.empty()
		when(throttle.reverser) {
			Throttles.Reverser.Forward -> text.append(RailXLang.of(RailXLang.ThrottleHudReverserForward))
			Throttles.Reverser.Neutral -> text.append(RailXLang.of(RailXLang.ThrottleHudReverserNeutral))
			Throttles.Reverser.Backward -> text.append(RailXLang.of(RailXLang.ThrottleHudReverserBackward))
		}
		text.append(" ")
		
		if(throttle.gear == -Throttles.maxBreak - 1) {
			text.append(RailXLang.of(RailXLang.ThrottleHudGearEmergencyBrake))
		} else {
			text.append(RailXLang.of(
				when {
					throttle.gear > 0 -> RailXLang.ThrottleHudGearForward
					throttle.gear == 0 -> RailXLang.ThrottleHudGearNeutral
					else -> RailXLang.ThrottleHudGearBackward
				}
			))
			text.append("${abs(throttle.gear)}")
		}
		
		graphics.drawString(mc.font, text, 16, graphics.guiHeight() - 29, 0xffffff)
		
		val text2 = RailXLang.of(
			RailXLang.ThrottleHudSpeed,
			Mth.floor(abs(carriage.train.speed) * 20 * 36 / 10),
		)
		graphics.drawString(mc.font, text2, 16, graphics.guiHeight() - 29 - 12, 0xffffff)
	}
}
