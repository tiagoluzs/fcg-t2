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
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author tiagoluz
 */
public class Explosao extends Element {

    public Explosao() {
        super();       
    }

    Explosao(Canhao canhao) {
       this.x = canhao.x + 100;
       this.y = canhao.y + 100;
       this.z = canhao.z;
    }

    @Override
    public void draw() {
        if(remover) {
            return;
        }
        
        this.calculaIdade();
        
        System.out.println(this.start);
        System.out.println(this.idade);
                
        if (idade > 5) {
            this.remover = true;
            return;
        }

        double angGirar = 360 / idade;
        
        

        glPushMatrix();

        glTranslated(x, y, z);

        glColor4f(1f, 1f, 0f, 1f);

        glRotated(angGirar, 0, 0, 1);

        glBegin(GL11.GL_POLYGON);

        glVertex2f(0, 10);
        glVertex2f(10, 30);
        glVertex2f(10, 20);
        glVertex2f(30, 20);

        glVertex2f(10, 0);
        glVertex2f(20, -30);
        glVertex2f(0, -10);
        glVertex2f(-20, -30);

        glVertex2f(-20, -10);
        glVertex2f(-30, -10);

        glEnd();

        glPopMatrix();

        glPushMatrix();

        glTranslated(x, y, z);

        glLineWidth(3);

        glColor4f(1f, 0f, 0f, 1f);
        glRotated(angGirar * -1, 0, 0, 1);

        glBegin(GL11.GL_POLYGON);

        glVertex2f(0, 10);
        glVertex2f(10, 30);
        glVertex2f(10, 20);
        glVertex2f(30, 20);

        glVertex2f(10, 0);
        glVertex2f(20, -30);
        glVertex2f(0, -10);
        glVertex2f(-20, -30);

        glVertex2f(-20, -10);
        glVertex2f(-30, -10);

        glEnd();

        glPopMatrix();
    }

}
