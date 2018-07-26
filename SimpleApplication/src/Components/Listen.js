import React, {Component} from 'react'
import SpotifyWebApi from 'spotify-web-api-js';
import './Listen.css';
import Slider from 'react-rangeslider'
import 'react-rangeslider/lib/index.css'
const spotifyApi = new SpotifyWebApi();


export default class Listen extends Component {
  constructor(){
  super();
  const params = this.getHashParams();
  const token = params.access_token;
  if (token) {
    spotifyApi.setAccessToken(token);
  }
  this.state = {
    loggedIn: token ? true : false,
    nowPlaying: { name: 'Name', artists: "Artists", albumArt: '' },
    isplaying: false,
    value: 50
  }
  spotifyApi.setVolume(50, {});
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

  render() {

    this.getPlaybackState();

    const { value } = this.state

    const { artists } = this.state.nowPlaying
    let text = ""
    let i
    for(i = 0; i<artists.length-1; i++){
      text += artists[i].name + ", "
    }
    text += artists[i].name




    return (

<div id="container">
      {this.state.loggedIn &&
  <div id="loged" className="loged-display">
  <div id="content">
        Hier anderer Content
  </div>
  <div id="player" className="player">
        <div id="album-img">
          <img src={this.state.nowPlaying.albumArt} style={{ height: 55 }}/>
        </div>
          <div id="song-name">
          { this.state.nowPlaying.name }
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
