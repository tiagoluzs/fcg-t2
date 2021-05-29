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

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author tiagoluz
 */
public class Canhao extends Element {

    int angulo;
    int forca = 2;

    Canhao() {
        super();
        z = 0;
        x = Main.WIDTH/2;
    }

    @Override
    public void draw() {

        glPushMatrix();

        glTranslated(x, y, z);
        
        
        this.minX = x;
        this.maxX = x+100;
        this.minY = 0;
        this.maxY = 50;
        
        // desenha base do canhão
        glLineWidth(3);
        glColor4f(1f, 1f, 1f, 1f);

        glBegin(GL11.GL_POLYGON);

        glVertex2f(0, 0);
        glVertex2f(0, 25);
        glVertex2f(25, 50);
        glVertex2f(75, 50);
        glVertex2f(100, 25);
        glVertex2f(100, 0);

        glEnd();

        glPopMatrix();

        glPushMatrix();

        glLoadIdentity();

        glTranslatef((x + 45), (y + 50), z);
        glRotatef(angulo, 0, 0, 1);
        
        glBegin(GL11.GL_POLYGON);

        int f = 20;
        if(this.forca == 1) {
            f = 10;
        } else if(this.forca == 2) {
            f = 20;
        } else if(this.forca == 3) {
            f = 40;
        } 
        
        glVertex2f(0, 0);
        glVertex2f(0, f);
        glVertex2f(5, f);
        glVertex2f(5, 0);

        
        
        glEnd();

        glPopMatrix();

    }

}
