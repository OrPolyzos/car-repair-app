class TextRectangle{
  constructor(x,y,description){
    this.x = x;
    this.y = y;
    this.description = description;
    this.stepx = random(-7,7);
    this.stepy = random(-4,4);
    this.col = color(random(255),random(255),random(255));
  }

  show(){ 
  textSize(50);
  fill(this.col);
  stroke(0);
  text(this.description, this.x, this.y);
  }
  
  move() {
    this.y += this.stepy;
    if (this.y < 25){
      this.stepy *= (-1);
    }
    else if (this.y > height-25){
      this.stepy *= (-1);
    }
    this.x += this.stepx;
    if (this.x <25){
      this.stepx *= (-1);
    }
    else if (this.x > width - 25){
      this.stepx *= (-1);
    }
  }
  
}