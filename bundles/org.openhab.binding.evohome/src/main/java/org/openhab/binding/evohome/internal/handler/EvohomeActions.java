package org.openhab.binding.evohome.internal.handler;

import java.time.Instant;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.automation.annotation.ActionInput;
import org.openhab.core.automation.annotation.RuleAction;
import org.openhab.core.thing.binding.ThingActions;
import org.openhab.core.thing.binding.ThingActionsScope;
import org.openhab.core.thing.binding.ThingHandler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Component(scope = ServiceScope.PROTOTYPE, service = EvohomeActions.class)
@ThingActionsScope(name = "evohome")
@NonNullByDefault
public class EvohomeActions implements ThingActions {

    private @Nullable EvohomeHeatingZoneHandler handler;

    @Override
    public void setThingHandler(@Nullable ThingHandler handler) {
        this.handler = handler instanceof EvohomeHeatingZoneHandler zoneHandler ? zoneHandler : null;
    }

    @Override
    public @Nullable ThingHandler getThingHandler() {
        return handler;
    }

    @RuleAction(label = "@text/actionSetTemporarySetPointLabel",
            description = "@text/actionSetTemporarySetPointDescription")
    public void setTemporarySetPoint(
            @ActionInput(name = "temperature", label = "@text/actionTemperatureLabel",
                    description = "@text/actionTemperatureDescription") @Nullable Double temperature,
            @ActionInput(name = "timeUntil", label = "@text/actionTimeUntilLabel",
                    description = "@text/actionTimeUntilDescription") @Nullable Instant timeUntil) {
        EvohomeHeatingZoneHandler zoneHandler = handler;
        if (zoneHandler == null || temperature == null || timeUntil == null) {
            throw new IllegalArgumentException("Zone, temperature, and end time are required");
        }
        zoneHandler.setTemporarySetPoint(temperature, timeUntil);
    }

    public static void setTemporarySetPoint(@Nullable ThingActions actions,
            @Nullable Double temperature, @Nullable Instant timeUntil) {
        if (actions instanceof EvohomeActions evohomeActions) {
            evohomeActions.setTemporarySetPoint(temperature, timeUntil);
        } else {
            throw new IllegalArgumentException("Instance is not an EvohomeActions class");
        }
    }
}
