package com.lhwdev.minecraft.railx.registry

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent


/**
 * Every player-facing text of railx.
 *
 * The english texts are declared here and become `assets/railx/lang/en_us.json` of the generated resources
 * (see [register], called during mod construction while running `runData`). Never hardcode display texts at
 * the call site; refer to a key of this object and turn it into a [Component] with [of] instead.
 *
 * Entries whose value equals their key are placeholders: they are already translatable, but no english text
 * has been written for them yet.
 */
object RailXLang {
	private val entries = mutableListOf<Pair<String, String>>()
	
	private fun entry(key: String, value: String): String {
		entries += key to value
		return key
	}
	
	private fun placeholder(key: String): String = entry(key, key)
	
	
	// keybinds; english texts are not written yet
	val KeyFlexiblePlacement = placeholder("key.railx.flexi_track.flexible_placement")
	val KeyThrottleAccelerate = placeholder("key.railx.throttle.accelerate")
	val KeyThrottleNeutral = placeholder("key.railx.throttle.neutral")
	val KeyThrottleBrake = placeholder("key.railx.throttle.brake")
	val KeyThrottleEmergencyBrake = placeholder("key.railx.throttle.emergency_brake")
	val KeyThrottleReverserForward = placeholder("key.railx.throttle.reverser_forward")
	val KeyThrottleReverserBackward = placeholder("key.railx.throttle.reverser_backward")
	
	val KeyCategories = entry("key.categories.railx", "RailX")
	
	
	val GenericSelect = entry("railx.generic.select", "Select")
	
	val CreativeTab = entry("railx.creative_tab", "RailX")
	
	
	// throttle
	val ThrottleHudReverserForward = entry("railx.throttle.hud.reverser.forward", "Forward")
	val ThrottleHudReverserNeutral = entry("railx.throttle.hud.reverser.neutral", "Neutral")
	val ThrottleHudReverserBackward = entry("railx.throttle.hud.reverser.backward", "Backward")
	
	val ThrottleHudGearForward = entry("railx.throttle.hud.gear.forward", "F")
	val ThrottleHudGearNeutral = entry("railx.throttle.hud.gear.neutral", "N")
	val ThrottleHudGearBackward = entry("railx.throttle.hud.gear.backward", "B")
	val ThrottleHudGearEmergencyBrake = entry("railx.throttle.hud.gear.emergency_brake", "EB")
	
	val ThrottleHudSpeed = entry("railx.throttle.hud.speed", "Speed: %s km/h")
	
	
	// realistic speed
	val RealisticSpeedHudSlip = entry("railx.realistic_speed.hud.slip", "Slip")
	
	
	// precise track overlay
	val PreciseOverlayTitlePlacement = entry("railx.precise_overlay.title.placement", "Placement")
	val PreciseOverlayTitleFlexiblePlacement =
		entry("railx.precise_overlay.title.flexible_placement", "Flexible Placement")
	val PreciseOverlayTitleHoveredCurve = entry("railx.precise_overlay.title.hovered_curve", "Hovered Curve")
	
	val PreciseOverlayAxis = entry("railx.precise_overlay.axis", "Axis = %s")
	val PreciseOverlayTilted = entry("railx.precise_overlay.tilted", " (Tilted)")
	val PreciseOverlayRadius = entry("railx.precise_overlay.radius", ", R=%s")
	
	val PreciseOverlayLineAxis = entry("railx.precise_overlay.line.axis", " | Axis: %s")
	val PreciseOverlayLineAxisSecond = entry("railx.precise_overlay.line.axis_second", " -> %s")
	val PreciseOverlayLineDelta = entry("railx.precise_overlay.line.delta", ", Δ=%s")
	val PreciseOverlayLineLength = entry("railx.precise_overlay.line.length", ", L=%s")
	val PreciseOverlayLineGradient = entry("railx.precise_overlay.line.gradient", ", Grad=%s")
	val PreciseOverlayLineOr = entry("railx.precise_overlay.line.or", " or %s")
	val PreciseOverlayLineFrom = entry("railx.precise_overlay.line.from", "From")
	val PreciseOverlayLineTo = entry("railx.precise_overlay.line.to", "To")
	val PreciseOverlayLineRadiusSuffix = entry("railx.precise_overlay.line.radius_suffix", ": R=%s")
	val PreciseOverlayLineAt = entry("railx.precise_overlay.line.at", ", at %s")
	
	
	// flexi track: placement overlay
	val FlexiTrackOverlayCannotPlaceFlexibly = entry(
		"railx.flexi_track.overlay.cannot_place_flexibly",
		"This track cannot be placed flexibly",
	)
	val FlexiTrackOverlayHold = entry("railx.flexi_track.overlay.hold", "Hold %s for flexible placement")
	val FlexiTrackOverlayToggle = entry("railx.flexi_track.overlay.toggle", "Press %s to toggle flexible placement")
	
	val FlexiTrackPlaceErrorPavementNotSupported =
		entry("railx.flexi_track.place_error.pavement_not_supported", "pavement not supported for track plan")
	val FlexiTrackPlaceErrorCurveTooLong = entry("railx.flexi_track.place_error.curve_too_long", "curve too long")
	val FlexiTrackPlaceErrorTooClose = entry("railx.flexi_track.place_error.too_close", "too close")
	val FlexiTrackPlaceErrorNotTrackBlockItem =
		entry("railx.flexi_track.place_error.not_track_block_item", "item is not TrackBlockItem")
	val FlexiTrackPlaceErrorInternal = entry(
		"railx.flexi_track.place_error.internal",
		"internal error: %s",
	)
	
	
	// flexi track: rotation
	val FlexiTrackRotateLabel = entry("railx.flexi_track.rotate.label", "Rotate Flexi Track")
	val FlexiTrackRotateLabelWithKind = entry("railx.flexi_track.rotate.label_with_kind", "Rotate Flexi Track %s")
	val FlexiTrackRotateScreenLabel = entry("railx.flexi_track.rotate.screen_label", "Set Flexi Track %s")
	val FlexiTrackRotateCannotRotate = entry("railx.flexi_track.rotate.cannot_rotate", "Cannot rotate track")
	val FlexiTrackRotateShortClickHint = entry(
		"railx.flexi_track.rotate.short_click_hint",
		"Short click to cycle between %s, %s, and %s.",
	)
	
	val FlexiTrackRotateKindDirection = entry("railx.flexi_track.rotate.kind.direction", "Direction")
	val FlexiTrackRotateKindGradient = entry("railx.flexi_track.rotate.kind.gradient", "Gradient")
	val FlexiTrackRotateKindCant = entry("railx.flexi_track.rotate.kind.cant", "Cant")
	
	val FlexiTrackRotateModePrecise = entry("railx.flexi_track.rotate.mode.precise", "Precise")
	val FlexiTrackRotateModeCoarse = entry("railx.flexi_track.rotate.mode.coarse", "Coarse")
	val FlexiTrackRotateModeSuffix = entry("railx.flexi_track.rotate.mode.suffix", " Mode")
	
	val FlexiTrackRotateClipboardName = entry("railx.flexi_track.rotate.clipboard_name", "Curve Rotation")
	
	
	// advanced roller
	val AdvancedRollerTipApplyMaterial = entry(
		"railx.advanced_roller.tip.apply_material",
		"Click with material to apply to copycat",
	)
	val AdvancedRollerTipPreviously = entry("railx.advanced_roller.tip.previously", ", previously %s")
	val AdvancedRollerAppliedMaterial = entry("railx.advanced_roller.applied_material", "Applied material %s to %s")
	
	
	// advanced track observer
	val AdvancedTrackObserverScreenTitle = entry("railx.advanced_track_observer.screen.title", "Advanced Observer")
	val AdvancedTrackObserverScreenInsertLuaRule =
		entry("railx.advanced_track_observer.screen.insert_lua_rule", "Insert lua rule")
	
	
	// middle track
	val MiddleTrackOptimizeFakeTracksRequired = entry(
		"railx.middle_track.optimize_fake_tracks_required",
		"railx: common.optimizeFakeTracks is turned off, but required for middleTrack feature. turning it on...",
	)
	
	
	// compatibility: Pantographs and Wires
	val CompatPantographsWiresAutoCantilever = entry(
		"railx.compat.pantographs_and_wires.auto_cantilever",
		"Cantilever previous: width=%s yOffset=%s new: width=%s yOffset=%s",
	)
	
	
	// manual station
	val ManualStationPressToArrive = entry("railx.manual_station.press_to_arrive", "Press %s to arrive at %s")
	val ManualStationStopAndPressToArrive = entry(
		"railx.manual_station.stop_and_press_to_arrive",
		"Stop and press %s to arrive at %s",
	)
	val ManualStationApproaching = entry("railx.manual_station.approaching", "Approaching %s")
	val ManualStationDistance = entry("railx.manual_station.distance", ": %s%s")
	
	
	// commands
	val CommandErrorNoWorldEdit = entry("railx.command.error.no_worldedit", "requires worldedit mod for region selection")
	val CommandErrorNoSelection = entry("railx.command.error.no_selection", "no selection")
	val CommandErrorNoFramedBlocks = entry("railx.command.error.no_framedblocks", "requires FramedBlocks mod")
	val CommandFixTrackGraphSuccess =
		entry("railx.command.fix_track_graph.success", "Successfully updated all track nodes in selection")
	val CommandFixBrokenFramedTrainsSuccess = entry(
		"railx.command.fix_broken_framed_trains.success",
		"Successfully updated every trains. Please rejoin world from client.",
	)
	
	
	// build track
	val BuildTrackDummyTrackPlanName = entry("railx.build_track.track_plan.dummy_name", "dummy")
	
	
	fun of(key: String, vararg args: Any): MutableComponent = Component.translatable(key, *args)
	
	/** Feeds all entries above into the lang data generator of [registrate]. */
	fun register(registrate: RailXRegistrate) {
		for((key, value) in entries) registrate.addRawLang(key, value)
	}
}
