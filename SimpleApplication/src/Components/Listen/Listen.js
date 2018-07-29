import React, {Component} from 'react'
import SpotifyWebApi from 'spotify-web-api-js';
import './Listen.css';
import Slider from 'react-rangeslider'
import 'react-rangeslider/lib/index.css'
import 'simplebar'; // or "import SimpleBar from 'simplebar';" if you want to use it manually.
import 'simplebar/dist/simplebar.css';
import { Grid, Row, Col } from 'react-flexboxgrid';
import Room from './Room/Room.js';
import {DragDropContext, Droppable, Draggable} from 'react-beautiful-dnd';
import SongSearch from './Search/SongSearch.js';

const spotifyApi = new SpotifyWebApi();
const registerUser = 'http://localhost:8080/hear-my-song-web/rest/user/register';


export default class Listen extends Component {
    constructor(){
        super();
        const params = this.getHashParams();
        const token = params.access_token;
        const refresh_token = params.refresh_token;
        if (token) {
            spotifyApi.setAccessToken(token);
        }

        this.handleChangeRoom = this.handleChangeRoom.bind(this);
        this.handleSubmitRoom = this.handleSubmitRoom.bind(this);

        let roomarray = [];
        roomarray[0] = "HMS - Mostplayed";
        roomarray[1] = "HMS - Rock";
        roomarray[2] = "HMS - Pop";
        roomarray[3] = "HMS - House";
        roomarray[4] = "HMS - Metal";
        roomarray[5] = "HMS - Funk";
        roomarray[6] = "HMS - Soul";
        roomarray[7] = "HMS - RNB";
        roomarray[8] = "HMS - Black";
        roomarray[9] = "HMS - Classic";

        this.state = {
            token: token,
            color: 'white',
            loggedIn: token ? true : false,
            nowPlaying: { name: 'Name', artists: "Artists", albumArt: '' },
            isplaying: false,
            value: 50,
            rooms: null,
            currentRoom: null,
            valueRoom: '',
            rooms: roomarray,
            roomlist: 'Show Rooms',
            refresh_token: refresh_token
        }
        spotifyApi.setVolume(50, {});
        this.createUser();
    }


  createUser = (url = registerUser, data = {
  "name": "Username",
  "service": 1,
  "accessToken": this.state.token,
  "refreshToken": this.state.refresh_token
}) => {
      // Default options are marked with *
        return fetch(url, {
            method: "PUT", // *GET, POST, PUT, DELETE, etc.
            mode: "cors", // no-cors, cors, *same-origin
            cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
            credentials: "omit", // include, same-origin, *omit
            headers: {
                "Content-Type": "application/json; charset=utf-8",
                // "Content-Type": "application/x-www-form-urlencoded",
            },
            redirect: "follow", // manual, *follow, error
            referrer: "no-referrer", // no-referrer, *client
            body: JSON.stringify(data), // body data type must match "Content-Type" header
        })
        .then(response => response.json()) // parses response to JSON
        .catch(error => console.error(`Fetch Error =\n`, error));
    };


    setRoom = (currentRoom) => {
        this.setState({ currentRoom: currentRoom })
    }

    getHashParams(){
        var hashParams = {};
        var e, r = /([^&;=]+)=?([^&;]*)/g,
            q = window.location.hash.substring(1);
        e = r.exec(q)
        while (e) {
            hashParams[e[1]] = decodeURIComponent(e[2]);
            e = r.exec(q);
        }
        return hashParams;
    }

    sleep(milliseconds) {
        var start = new Date().getTime();
        for (var i = 0; i < 1e7; i++) {
            if ((new Date().getTime() - start) > milliseconds){
                break;
            }
        }
    }

    getPlaybackState(){
        this.sleep(70);
        spotifyApi.getMyCurrentPlaybackState()
            .then((response) => {
                this.setState({
                    nowPlaying: {
                        name: response.item.name,
                        artists: response.item.artists,
                        albumArt: response.item.album.images[0].url
                    },
                    volume: response.device.volume_percent,
                    isplaying: response.is_playing,
                });
            })
    }

    pauseSong(){
        spotifyApi.pause({});
        this.getPlaybackState();
    }

    playSong(){
        spotifyApi.play({});
        this.getPlaybackState();
    }

    handleChange = value => {
        this.setState({
            value: value
        })
        spotifyApi.setVolume(value, {});
    };

    handleChangeRoom(event) {
        this.setState({valueRoom: event.target.value});
    }

    handleSubmitRoom(event) {
        this.setState({currentRoom: this.state.valueRoom});
        this.addRoom();
        event.preventDefault();
    }

    addRoom(newRoom){
        var roomarray = this.state.rooms;
        roomarray.push(this.state.valueRoom)
        this.setState({
            rooms: roomarray});
    }

    toggleRoomlist() {
        let div = document.getElementById('leftbar');
        div.style.display = div.style.display == "block" ? (div.style.display ="none", this.setState({
            roomlist: 'Show Rooms'}))  : (div.style.display = "block", this.setState({
            roomlist: 'Hide Rooms'}));
        let diva = document.getElementById('center');
        diva.style.display = div.style.display == "block" ? "none" : "block";
        let divb = document.getElementById('rightbar');
        divb.style.display = div.style.display == "block" ? "none" : "block";

    }

    render() {


      /*

      SPOTIFY WEBPLAYBACK

      */

      window.onSpotifyWebPlaybackSDKReady = () => {
             const Spotify = window.Spotify;
            const token = this.state.token;
            const player = new Spotify.Player({
              name: 'Hear My Song',
              getOAuthToken: cb => { cb(token); }
            });

            // Error handling
            player.addListener('initialization_error', ({ message }) => { console.error(message); });
            player.addListener('authentication_error', ({ message }) => { console.error(message); });
            player.addListener('account_error', ({ message }) => { console.error(message); });
            player.addListener('playback_error', ({ message }) => { console.error(message); });

            // Playback status updates
            player.addListener('player_state_changed', state => { console.log(state); });

            // Ready
            player.addListener('ready', ({ device_id }) => {
              console.log('Ready with Device ID', device_id);
            });

            // Not Ready
            player.addListener('not_ready', ({ device_id }) => {
              console.log('Device ID has gone offline', device_id);
            });

            // Connect to the player!
            player.connect();
          };


      /*

      SPOTIFY WEBPLAYBACK

      */


        this.getPlaybackState()
        const { value } = this.state

        const { artists } = this.state.nowPlaying
        let text = ""
        let i
        for(i = 0; i<artists.length-1; i++){
            text += artists[i].name + ", "
        }
        text += artists[i].name

        let listOfRooms = [];
        if(this.state.rooms != null){
            let c;
            for(c = 0; c < this.state.rooms.length; c++){
                let rname = this.state.rooms[c]
                listOfRooms[c] = <div className="roombuttons" onClick={() => this.setRoom(rname)}><div className="roombuttoninfo">{rname} <img src={require('./icons/key.png')} height="15" width="15" /> 56 <img src={require('./icons/listeners.png')} height="18" width="18" /><br/>
                    <div className="tags"> Tags: <u>Cool</u>, <u>Brandnew</u>, <u>Insider</u>, <u>GoodVibes</u></div></div></div>
            }
        }

        return (
            <div id="container">
                {this.state.loggedIn &&
                <div id="loged" className="loged-display">
                    <div id="content" class="row">
                        <div id="showrooms" class="col-xs-12 col-sm-12">
                            <button onClick={() => this.toggleRoomlist()}>{this.state.roomlist}</button>
                        </div>
                        <div id="leftbar" class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                            <div data-simplebar id="roomlist">
                                {listOfRooms}
                            </div>
                        </div>
                        <div id="center" class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
                            <Room/>
                        </div>
                        <div id="rightbar" class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
                            <SongSearch/>
                        </div>
                    </div>
                    {this.state.currentRoom == null &&
                    <div id="footer" className="footer">
                    </div>
                    }

                    {this.state.currentRoom != null &&
                    <div id="player" className="player">
                        <div id="album-img">
                            <img src={this.state.nowPlaying.albumArt} style={{ height: 55 }}/>
                        </div>
                        <div id="song-name">
                            <div id="onlyname">
                                <p>{ this.state.nowPlaying.name }</p>
                            </div>
                            <div id="song-artist">
                                { text }
                            </div>
                        </div>
                        <div id="playpause">
                            {!this.state.isplaying &&
                            <img src={require('./icons/playbutton.png')} onClick={() => this.playSong()} />
                            }
                            {this.state.isplaying &&
                            <img src={require('./icons/pausebutton.png')} onClick={() => this.pauseSong()} />
                            }
                        </div>
                        <div id ="slider" className='slider'>
                            <div className='slider orientation-reversed'>
                                <div className='slider-group'>
                                    <div className='slider-horizontal'>
                                        <Slider
                                            min={0}
                                            max={100}
                                            value={value}
                                            orientation='horizontal'
                                            onChange={this.handleChange}
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    }
                </div>
                }

                {!this.state.loggedIn &&
                <div id="unloged" className="unloged-display">
                    <div>
                        <p>This Website is using the Spotify API.
                            You need a Spotify-Account to listen to Music.</p>
                    </div>
                    <div>
                        <a href='http://localhost:8888/login'> Login with Spotify </a>
                    </div>
                </div>
                }

            </div>
        );
    }
}
