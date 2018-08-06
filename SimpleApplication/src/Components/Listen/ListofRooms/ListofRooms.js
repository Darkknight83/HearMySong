import React, {
    Component
} from 'react';
import './ListofRooms.css'
import SimpleBar from 'simplebar-react';
import 'simplebar/dist/simplebar.min.css';

export default class ListofRooms extends Component {


    constructor(props) {
        super(props);


        this.state = {
            rooms: this.props.rooms
        }



    }

    render(){



      let listOfRooms = [];
      if(this.props.rooms != null){
          let c;
          console.log("In ListofRooms:");
          console.log(this.props.rooms);
          console.log("Out ListofRooms:");
          for(c = 0; c < this.props.rooms.length; c++){
              let rname = this.props.rooms[c].name
              listOfRooms[c] = <div className="roombuttons" onClick={() => this.props.setRoom(rname)}>
              <div className="roombuttoninfo">
              {rname}{" "}
              {this.props.rooms[c].password != null &&
              <img src={require('./icons/key.png')} height="15" width="15" />
              }
               {" "}{this.props.rooms[c].listeners}
               <img src={require('./icons/listeners.png')} height="18" width="18" />
               <br/>
               <div className="tags">
               {" "}Tags: <u>{this.props.rooms[c].tags}</u>
               {" "}Genre: {this.props.rooms[c].genre}
               </div>
               </div>
               </div>
             }
           }


      return(
        <div id="roomlist" data-simplebar>
        <div>{listOfRooms}</div>
        </div>
      );
    }
  }
