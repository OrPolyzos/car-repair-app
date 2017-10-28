class Decoration {
  constructor(x,y,r) {
    this.x = x;
    this.y = y;
    this.r = r;
    this.stepx = random(-2,2);
    this.stepy = random(-2,2);
    this.col = color(random(255),random(255),random(255));
  }
  
  move() {
    this.x += this.stepx;
    this.y += this.stepy;
    if (this.x < 0){
      this.x = width;
    }
    else if (this.x > width){
      this.x = 0;
    }
    if (this.y < 0){
      this.y = height;
    }
    else if (this.y > height){
      this.y = 0;
    }
  }
  
  show(){
    stroke(0);
    fill(this.col);
    ellipse(this.x,this.y,this.r * 2,this.r * 2);   
  }
  
  wasClicked(clickedx,clickedy){
    var d = dist(clickedx,clickedy,this.x,this.y);
    if (d <= this.r){
      return true;
    }
    return false;
  }
  
}
