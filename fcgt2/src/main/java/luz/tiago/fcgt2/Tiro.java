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
public class Tiro extends Element {

    private final int angulo;
    private final double forca;
    int tipo;

    Tiro(Canhao canhao) {
        this.x = canhao.x;
        this.y = canhao.y;
        this.z = canhao.z;
        this.angulo = canhao.angulo;
        this.forca = canhao.forca;
        tipo = 1;
    }

    Tiro(Aviao aviao) {
        this.x = aviao.x;
        this.y = aviao.y;
        this.z = aviao.z;
        this.angulo = 0;
        this.forca = 0.0;
        tipo = 2;
    }

    @Override
    public void draw() {
        if(this.remover) {
            return;
        }
        calculaIdade();
        switch (tipo) {
            case 1:
                drawTiroCanhao();
                break;
            default:
                drawTiroAviao();
                break;
        }
    }
    
    void drawFogo() {
        // desenha fogo
        if (new Date().getTime() % 2 == 0) {
            glColor4f(1f, 1f, 0f, 1f);
        } else {
            glColor4f(1f, 0f, 0f, 1f);
        }

        glBegin(GL11.GL_POLYGON);

        glVertex2f(3, 0);
        glVertex2f(0, -3);
        glVertex2f(3, -2);
        glVertex2f(5, -6);
        glVertex2f(7, -2);
        glVertex2f(10, -3);
        glVertex2f(7, 0);

        glEnd();
    }
    
    void drawBala() {
        glBegin(GL11.GL_POLYGON);

        glVertex2f(0, 0);
        glVertex2f(0, 10);
        glVertex2f(5, 15);
        glVertex2f(10, 10);
        glVertex2f(10, 0);

        glEnd();
    }
    
    void drawTiroCanhao() {
        glPushMatrix();
        
        double vel = 30.0+(this.forca*40.0);
        
        double Yinit = 50; // + 40 + ((this.forca-1) * 20);
        
        // calcula x MRU 
        double anguloCalc = Math.toRadians(90+this.angulo); 
        
        double novoX = (x + 45 + (idade/1000 * vel * Math.cos(anguloCalc)));
        
        // calcula y MRUV
        double gravidade = 30.0;
        
        double t = (idade/1000.0);
        double novoY = Yinit + vel * t + ((gravidade*-1) * Math.pow(t,2)/2);
        
        // desenha bala
        glTranslated(novoX, novoY, z);

        glLineWidth(3);
        glColor4f(1f, 1f, 1f, 1f);

        drawBala();

        drawFogo();

        glPopMatrix();
    }

    void drawTiroAviao() {
        glPushMatrix();

        // desenha bala
        
        // calcula x MRU 
        double velX = Main.WIDTH / 15.0;
        double novoX = (x + 50 - (idade/1000 * velX));

        // calcula y MRUV
        int gravidade = 30;
        
        double novoY = y - (gravidade * Math.pow(idade/1000, 2) / 2);
        
        
        glTranslated(novoX, novoY, z);
        
        GL11.glRotated(180, 0, 0, 1);

        glLineWidth(3);
        
        glColor4f(1f, 0f, 0f, 1f);

        drawBala();

        drawFogo();

        glPopMatrix();
    }

}
