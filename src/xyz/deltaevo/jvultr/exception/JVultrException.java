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
 * A JVultr Exception
 */
public class JVultrException extends Exception{
    private final int responceCode;

    public JVultrException(String cause, int responseCode){
        super(cause + " | Http Response code " + responseCode);
        this.responceCode = responseCode;
    }

    public JVultrException(String cause, int responseCode, Exception ex){
        super(cause + " | Http Response code " + responseCode , ex);
        this.responceCode = responseCode;
    }

    public int getResponceCode() {
        return responceCode;
    }
}
