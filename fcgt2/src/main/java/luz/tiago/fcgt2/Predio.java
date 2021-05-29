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

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author tiagoluz
 */
public class Predio extends Element {

    int tipo;

    @Override
    public void draw() {

        if (remover == true) {
            return;
        }

        glPushMatrix();

        glTranslated(x, y, z);

        switch (tipo) {
            case 0:
                drawTipo1();
                this.minX = x;
                this.maxX = x + 30;
                this.minY = 0;
                this.maxY = 100;
                break;
            case 1:
                drawTipo2();
                this.minX = x - 10;
                this.maxX = x + 80;
                this.minY = 0;
                this.maxY = 30;
                break;
            case 2:
                drawTipo3();
                this.minX = x + 10;
                this.maxX = x + 40;
                this.minY = 0;
                this.maxY = 110;
                break;
            default:
                drawTipo1();
        }

        glPopMatrix();

    }

    private void drawTipo1() {

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(0f, 0f, 1f, 1f);

        glVertex2f(0, 0);
        glVertex2f(0, 100);
        glVertex2f(30, 100);
        glVertex2f(30, 0);

        glEnd();

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(0f, 1f, 0f, 1f);

        glVertex2f(10, 10);
        glVertex2f(10, 20);
        glVertex2f(20, 20);
        glVertex2f(20, 10);

        glEnd();

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(0f, 1f, 0f, 1f);

        glVertex2f(10, 30);
        glVertex2f(10, 50);
        glVertex2f(20, 50);
        glVertex2f(20, 30);

        glEnd();

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(0f, 1f, 0f, 1f);

        glVertex2f(10, 60);
        glVertex2f(10, 80);
        glVertex2f(20, 80);
        glVertex2f(20, 60);

        glEnd();

    }

    private void drawTipo2() {

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(0f, 0f, 1f, 1f);

        glVertex2f(-10, 0);
        glVertex2f(-10, 30);
        glVertex2f(80, 30);
        glVertex2f(80, 0);

        glEnd();

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(1f, 1f, 1f, 1f);

        glVertex2f(0, 10);
        glVertex2f(0, 20);
        glVertex2f(10, 20);
        glVertex2f(10, 10);

        glEnd();

        glBegin(GL_POLYGON);

        glVertex2f(30, 10);
        glVertex2f(30, 20);
        glVertex2f(40, 20);
        glVertex2f(40, 10);

        glEnd();

        glBegin(GL_POLYGON);

        glVertex2f(60, 10);
        glVertex2f(60, 20);
        glVertex2f(70, 20);
        glVertex2f(70, 10);

        glEnd();

    }

    private void drawTipo3() {

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(1f, 0f, 0f, 1f);

        glVertex2f(10, 0);
        glVertex2f(10, 110);
        glVertex2f(40, 110);
        glVertex2f(40, 0);

        glEnd();

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(1f, 1f, 0f, 1f);

        glVertex2f(15, 10);
        glVertex2f(15, 20);
        glVertex2f(25, 20);
        glVertex2f(25, 10);

        glEnd();

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(1f, 1f, 0f, 1f);

        glVertex2f(15, 30);
        glVertex2f(15, 50);
        glVertex2f(25, 50);
        glVertex2f(25, 30);

        glEnd();

        glBegin(GL_POLYGON);

        glLineWidth(3);
        glColor4f(1f, 1f, 0f, 1f);

        glVertex2f(15, 60);
        glVertex2f(15, 80);
        glVertex2f(25, 80);
        glVertex2f(25, 60);

        glEnd();

    }

}
