class TextBubble{
  constructor(x,y,letter){
    this.x = x;
    this.y = y;
    this.letter = letter;
    this.stepy = random(5,7.5);
    this.col = color(random(255),random(255),random(255));
  }

  show(){ 
  noStroke();
  fill(this.col);
  ellipse(this.x,this.y,100,100);
  textSize(50);
  fill(255);
  text(this.letter, this.x-14, this.y+18);
  }
  
  move() {
    this.y += this.stepy;
    if (this.y < 50){
      this.stepy *= (-1);
    }
    else if (this.y > height-50){
      this.stepy *= (-1);
    }
  }
  
  wasClicked(clickedx,clickedy){
    var d = dist(clickedx,clickedy,this.x,this.y);
    if (d <= 50){
      return true;
    }
    return false;
  }
  
}