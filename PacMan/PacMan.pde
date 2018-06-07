int radius = 15;
float x = 250;
float y = 250;

ArrayList<Particle> dots = new ArrayList();

class Particle {
  int x, y;
  Particle(int x, int y) {
    this.x = x;
    this.y = y;
  }
  void display() {
    fill(255, 215, 0);
    ellipse(x, y, 10, 10);
  }
}

void setup() {
  size(760, 500);
  ellipseMode(RADIUS);
  for (int i=0; i<50; i++) {
    Particle P = new Particle((int)random(width), (int)random(height));
    dots.add(P);
  }
}
 
void draw() {
  background(0);
  fill (0, 175, 255);
  render();
 
  for (int i = 0; i < dots.size(); i++) {
    Particle Pn = (Particle) dots.get(i);
    Pn.display();
    if (dist(x, y, Pn.x, Pn.y) < radius) {
      dots.remove(i);
    }
  }
}

void render() {
  for (int i=-1; i < 2; i++) {
    for (int j=-1; j < 2; j++) {
      pushMatrix();
      translate(x + (i * width), y + (j*height));
      if ( direction == -1) { 
        rotate(PI);
      }
      if ( direction2 == 1) { 
        rotate(HALF_PI);
      }
      if ( direction2 == -1) { 
        rotate( PI + HALF_PI );
      }
      arc(0, 0, radius, radius, map((millis() % 500), 0, 500, 0, 0.52), map((millis() % 500), 0, 500, TWO_PI, 5.76) );
      popMatrix();
    }
  }
}
