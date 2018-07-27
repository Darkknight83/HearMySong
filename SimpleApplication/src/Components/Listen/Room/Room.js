import React, {
    Component
} from 'react';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';
import './Room.css'



class RoomEntity {

    constructor(name, password, owner, songs) {
        this.name = name;
        this.password = password;
        this.owner = owner;
        this.songs = songs;
    }
}


const grid = 8;

const getItemStyle = (isDragging, draggableStyle) => ({
  // some basic styles to make the items look a bit nicer
  userSelect: 'none',
  padding: grid * 2,
  margin: `0 0 ${grid}px 0`,

  // change background colour if dragging
  background: isDragging ? 'lightgrey' : 'grey',

  // styles we need to apply on draggables
  ...draggableStyle,
});

const getListStyle = isDraggingOver => ({
  background: isDraggingOver ? 'rgba(62, 62, 62, 1)' : 'lightgrey',
  padding: grid,
  width: 250,

});



export default class Room extends Component {
    
    constructor() {
        super();
        this.state = {
            room: null,
            items: []
        };
    }


    createRoom() {
        this.setState({room: new Room()})
    }

    leaveRoom() {
        this.setState({room: null})
    }

    render(){
        const divClassName = 'room';
        if(this.state.room == null){
        return(
            <div className={divClassName}>
                <form style={{color: 'black'}}>
                    <label>
                        Roomname:
                        <input type="text" name="Roomname" />
                    </label>
                    <br/>
                    <label>
                        Password:
                        <input type="password" name="password" />
                    </label>
                    <br/>
                    <button onClick={() => this.createRoom()} type="button" name="Create" style={{color: 'black'}}>Create</button>
                </form>
            </div>
        );
        } else {
            return(
            <div className={divClassName}>
            <Droppable droppableId="droppable">
            {(provided, snapshot) => (
                <div
                    ref={provided.innerRef}
                    style={getListStyle(snapshot.isDraggingOver)}
                >
                {this.state.items.map((item, index) => (
                    <Draggable key={item.id} draggableId={item.id} index={index}>
                    {(provided, snapshot) => (
                        <div
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                            style={getItemStyle(
                                snapshot.isDragging,
                                provided.draggableProps.style
                            )}
                            >
                            <div id="flex-container">
                              <span className="song-info" id="flex">{item.song.songname}</span>
                              <span contentEditable='true' onClick={this.addItemToRoom(item)} className="add-button" id="raw"><i className="fa fa-plus"></i></span>
                            </div>
                        </div>
                    )}
                    </Draggable>
                ))}
                {provided.placeholder}
                </div>
            )}
            </Droppable>
            <button onClick={() => this.leaveRoom()} type="button" name="Leave" style={{color: 'black'}}>Leave</button>
            </div>
        );
        }
    }
}