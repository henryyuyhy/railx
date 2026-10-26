package com.lhwdev.minecraft.railx.ccAdvanced.advancedTrackObserver

import com.lhwdev.minecraft.railx.registry.RailXLang
import com.simibubi.create.foundation.gui.AllGuiTextures
import com.simibubi.create.foundation.gui.AllIcons
import com.simibubi.create.foundation.gui.widget.IconButton
import net.createmod.catnip.gui.AbstractSimiScreen
import net.createmod.catnip.platform.CatnipServices
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.EditBox
import net.minecraft.network.chat.Component


class ObserverConfigureScreen(val blockEntity: AdvancedTrackObserverBlockEntity) :
	AbstractSimiScreen(RailXLang.of(RailXLang.AdvancedTrackObserverScreenTitle)) {
	private val background = AllGuiTextures.SCHEDULE
	
	private lateinit var codeRule: EditBox
	
	override fun init() {
		setWindowSize(background.width, background.height)
		super.init()
		
		codeRule = EditBox(
			font,
			guiLeft + 16, guiTop + 16,
			windowWidth - 32, windowHeight - 32,
			RailXLang.of(RailXLang.AdvancedTrackObserverScreenInsertLuaRule)
		)
		codeRule.setMaxLength(512)
		codeRule.value = blockEntity.rule.code
		addRenderableWidget(codeRule)
		
		val confirmButton = IconButton(guiLeft + windowWidth - 42, guiTop + windowHeight - 30, AllIcons.I_CONFIRM)
		confirmButton.withCallback<IconButton> { minecraft!!.player!!.closeContainer() }
		addRenderableWidget(confirmButton)
	}
	
	override fun renderWindow(graphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTicks: Float) {
		background.render(graphics, guiLeft, guiTop)
	}
	
	override fun removed() {
		CatnipServices.NETWORK.sendToServer(ObserverEditPacket(blockEntity.blockPos, codeRule.value))
	}
}
