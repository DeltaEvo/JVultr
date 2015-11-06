/*
 * Copyright 2015 DeltaEvolution
 *
 * This file is part of JVultr.
 * JVultr is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JVultr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JVultr. If not, see <http://www.gnu.org/licenses/>.
 */
package xyz.deltaevo.jvultr.exception;

/**
 * @author DeltaEvolution
 * Represent a Vultr 503 Error
 */
public class RateLimitExceeded extends JVultrException {

    public RateLimitExceeded() {
        super("Rate limit exceeded. API requests are limited to an average of 1/s. Try your request again later", 503);
    }
}
