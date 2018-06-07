int direction = 0;
int direction2 = 0;
 
void keyPressed() {
  if (key == CODED) {
    if (keyCode == LEFT) {
      x = x - 10;
      direction = -1;
      direction2 = 0;
    }
    else if (keyCode == RIGHT) {  
      x = x + 10;
      direction = 1;
      direction2 = 0;
    }
    else if (keyCode == UP) {
      y = y - 10;
      direction = 0;
      direction2 = -1;
    }
    else if (keyCode == DOWN) { 
      y = y + 10;
      direction = 0;
      direction2 = 1;
    }
  }
}
