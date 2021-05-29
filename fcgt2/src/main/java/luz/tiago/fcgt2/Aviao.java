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
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author tiagoluz
 */
public class Aviao extends Element {

    public static int count = 0;
    public int number;
    int tipo;
    private double idade;

    public Aviao() {
        super();
        this.number = ++count;
        alturaAleatoria();
    }

    @Override
    public String toString() {
        return "Aviao " + this.number + " (" + this.idade + ") => " + super.toString();
    }

    @Override
    public void draw() {
        if (remover == true) {
            return;
        }

        calculaIdade();
        if (this.idade > 1.2) {
            this.start = new Date().getTime();
            this.alturaAleatoria();
            return;
        }

        x = (int) (Main.WIDTH - (Main.WIDTH * idade));

        switch (tipo) {
            case 0:
                drawTipo1();
                break;
            case 1:
                drawTipo2();
                break;
            default:
                drawTipo3();
        }
    }

    void calculaIdade() {
        long now = new Date().getTime();
        this.idade = (now - this.start) / 15000.0;
    }

    void drawTipo1() {

        glPushMatrix();

        glTranslated(x, y, z);

        glLineWidth(3);
        glColor4f(1f, 0f, 0f, 1f);

        glBegin(GL11.GL_POLYGON);

        drawCorpo();

        glEnd();

        drawFogo();

        glPopMatrix();
    }

    void drawTipo2() {

        glPushMatrix();

        glTranslated(x, y, z);

        glLineWidth(3);
        glColor4f(0f, 1f, 0f, 1f);

        glBegin(GL11.GL_POLYGON);

        drawCorpo();

        glEnd();

        drawFogo();

        glPopMatrix();
    }

    void drawTipo3() {

        glPushMatrix();

        glTranslated(x, y, z);

        glLineWidth(3);
        glColor4f(1f, 0f, 1f, 1f);

        glBegin(GL11.GL_POLYGON);

        drawCorpo();

        glEnd();

        drawFogo();

        glPopMatrix();
    }

    void drawFogo() {
        // fogo
        // desenha fogo
        if (new Date().getTime() % 2 == 0) {
            glColor4f(1f, 1f, 0f, 1f);
        } else {
            glColor4f(1f, 0f, 0f, 1f);
        }

        glBegin(GL11.GL_POLYGON);

        glVertex2f(100, 20);
        glVertex2f(120, 10);
        glVertex2f(115, 25);
        glVertex2f(130, 30);
        glVertex2f(115, 35);
        glVertex2f(120, 40);
        glVertex2f(100, 30);

        glEnd();

    }

    void drawCorpo() {
        glVertex2f(0, 0);
        glVertex2f(100, 0);
        glVertex2f(100, 50);
        glVertex2f(90, 20);
        
        glVertex2f(50, 20);
        glVertex2f(50, 45);
        glVertex2f(40, 20);
        glVertex2f(10, 20);
    }

    private void alturaAleatoria() {
        this.y = (int) (150 + (Math.random() * 350.0));
    }

}
