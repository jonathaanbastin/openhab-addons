/*
 * Copyright (c) 2010-2026 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.evohome.internal.api.models.v2.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * Request model for the mode
 *
 * @author Jasper van Zuijlen - Initial contribution
 *
 */
public class HeatSetPoint {

/**
     * Constructs a request to follow the schedule.
     */
    HeatSetPoint() {
        this(0.0, "FollowSchedule", null);
    }

    /**
     * Constructs a permanent override.
     *
     * @param setPoint target temperature
     */
    HeatSetPoint(double setPoint) {
        this(setPoint, "PermanentOverride", null);
    }

    /**
     * Constructs a temporary override ending at the given time.
     *
     * @param setPoint target temperature
     * @param timeUntil ISO-8601 end time, preferably UTC (for example,
     *                  {@code 2026-10-11T18:30:00Z})
     */
    HeatSetPoint(double setPoint, String timeUntil) {
        this(setPoint, "TemporaryOverride", timeUntil);
    }

    private HeatSetPoint(double setPoint, String setpointMode, String timeUntil) {
        // Preserve the binding's existing half-degree rounding.
        this.heatSetpointValue = Math.round(setPoint * 2) / 2.0;
        this.setpointMode = setpointMode;
        this.timeUntil = timeUntil;
    }

    @SuppressWarnings("unused")
    @SerializedName("heatSetpointValue")
    private double heatSetpointValue;

    @SuppressWarnings("unused")
    @SerializedName("setpointMode")
    private String setpointMode;

    @SuppressWarnings("unused")
    @SerializedName("timeUntil")
    private String timeUntil;
}
