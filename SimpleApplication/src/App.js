import React, { Component} from 'react';
import uuid from 'uuid';
import $ from 'jquery';
import './App.css';

class App extends Component {

    componentDidMount(){
    $(".sidebar").hide();
    $(".column-c").css("width" , "100%");

     $("#listen").click(function(){
        $(".sidebar").show();
        $(".column-c").css("width" , "80%");
    });
    $("#home").click(function(){
        $(".sidebar").hide();
        $(".column-c").css("width" , "100%");
    });
    $("#help").click(function(){
        $(".sidebar").hide();
        $(".column-c").css("width" , "100%");
    });
    $("#about").click(function(){
        $(".sidebar").hide();
        $(".column-c").css("width" , "100%");
    });
    }

 render() {
        return (
            <div class="App">          
            <link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet'/>
            <div class="header">
            <div class="topnav">
            <a href="#" id="home"><img src={require('./images/hms-ongrey-w.png')} width="250x" height="75px"/></a>    
            <ul>   
            <li><a href="#" id="listen">Listen</a></li>        
            <li><a href="#" id="help">Help</a></li>
            <li><a href="#" id="about">About</a></li>
            </ul>
            </div>
            </div>
            <div class="row">
  <div class="column-s">
    <div class = "sidebar">
        <p>Sidebar</p>
    </div>
  </div>
  <div class="column-c">
  <div class = "content">
  Barton did feebly change man she afford square add. Want eyes by neat so just must. Past draw tall up face show rent oh mr. Required is debating extended wondered as do. New get described applauded incommode shameless out extremity but. Resembled at perpetual no believing is otherwise sportsman. Is do he dispatched cultivated travelling astonished. Melancholy am considered possession on collecting everything. Ye on properly handsome returned throwing am no whatever. In without wishing he of picture no exposed talking minutes. Curiosity continual belonging offending so explained it exquisite. Do remember to followed yourself material mr recurred carriage. High drew west we no or at john. About or given on witty event. Or sociable up material bachelor bringing landlord confined. Busy so many in hung easy find well up. So of exquisite my an explained remainder. Dashwood denoting securing be on perceive my laughing so. 

In by an appetite no humoured returned informed. Possession so comparison inquietude he he conviction no decisively. Marianne jointure attended she hastened surprise but she. Ever lady son yet you very paid form away. He advantage of exquisite resolving if on tolerably. Become sister on in garden it barton waited on. 

No depending be convinced in unfeeling he. Excellence she unaffected and too sentiments her. Rooms he doors there ye aware in by shall. Education remainder in so cordially. His remainder and own dejection daughters sportsmen. Is easy took he shed to kind. 

Literature admiration frequently indulgence announcing are who you her. Was least quick after six. So it yourself repeated together cheerful. Neither it cordial so painful picture studied if. Sex him position doubtful resolved boy expenses. Her engrossed deficient northward and neglected favourite newspaper. But use peculiar produced concerns ten. 

Quick six blind smart out burst. Perfectly on furniture dejection determine my depending an to. Add short water court fat. Her bachelor honoured perceive securing but desirous ham required. Questions deficient acuteness to engrossed as. Entirely led ten humoured greatest and yourself. Besides ye country on observe. She continue appetite endeavor she judgment interest the met. For she surrounded motionless fat resolution may. 

Meant balls it if up doubt small purse. Required his you put the outlived answered position. An pleasure exertion if believed provided to. All led out world these music while asked. Paid mind even sons does he door no. Attended overcame repeated it is perceive marianne in. In am think on style child of. Servants moreover in sensible he it ye possible. 

Am finished rejoiced drawings so he elegance. Set lose dear upon had two its what seen. Held she sir how know what such whom. Esteem put uneasy set piqued son depend her others. Two dear held mrs feet view her old fine. Bore can led than how has rank. Discovery any extensive has commanded direction. Short at front which blind as. Ye as procuring unwilling principle by. 

An an valley indeed so no wonder future nature vanity. Debating all she mistaken indulged believed provided declared. He many kept on draw lain song as same. Whether at dearest certain spirits is entered in to. Rich fine bred real use too many good. She compliment unaffected expression favourable any. Unknown chiefly showing to conduct no. Hung as love evil able to post at as. 

Their could can widen ten she any. As so we smart those money in. Am wrote up whole so tears sense oh. Absolute required of reserved in offering no. How sense found our those gay again taken the. Had mrs outweigh desirous sex overcame. Improved property reserved disposal do offering me. 
  </div>
  </div>
</div>   
</div>      
           
        );
    }
}

export default App;