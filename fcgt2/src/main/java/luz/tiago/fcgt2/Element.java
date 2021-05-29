/*
 * Copyright (C) 2021 tiagoluz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package luz.tiago.fcgt2;

import java.util.Date;

/**
 *
 * @author tiagoluz
 */
public abstract class Element {
    public int x;
    public int y;
    public int z = 0;
    public int scale;
    public long start;
    public boolean remover = false;
    public double idade;
    
    Element() {
        this.start = new Date().getTime();
    }
    
    public abstract void draw();
    
    void calculaIdade() {
        long now = new Date().getTime();
        this.idade = (now - this.start) ;
    }
    
    @Override
    public String toString() {
        return this.x + ","+this.y + "," + this.z + "@" + this.start;
    }

}
