function fizzbuzz(min, max){
    interval = max-min;
    for (i = 0; i < interval; i++){
        if ((i + min)%3 == 0){
            document.write("fizz");
        }
        else if ( (i + min)%5 == 0 ){
            document.write("buzz");
        }
        else if ( (i + min)%3 == 0 & (i+min)%5==0 ) {
            document.write("fizzbuzz");
        }
        else {
            document.write((i+min));
        }
    }
}