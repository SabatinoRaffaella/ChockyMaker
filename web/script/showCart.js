 function hasClass(element, clsName) {
    return (' ' + element.className + ' ').indexOf(' ' + clsName + ' ') > -1;
    }
    $(document).ready(function(){
         $('#lpcart').hide();         
         $('#lg-bag').click(function(){
          if (hasClass(document.getElementById('lpcart'),"active")) {     
          document.getElementById('lpcart').classList.remove("active");
          $('#lpcart').hide();          
        }
        else
        { 
          document.getElementById('lpcart').classList.add("active");  
          $('#lpcart').show();                  
        }
    });
        
    });
