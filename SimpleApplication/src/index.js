import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import About from './Components/About.js';
import Help from './Components/Help.js';
import Listen from './Components/Listen.js';

import { BrowserRouter, Route} from 'react-router-dom'


ReactDOM.render((
		<BrowserRouter>
			<App>
				<Route path="/" component={Listen}/>
				<Route path="/about" component={About}/>
				<Route path="/help" component={Help}/>
			</App>		
		</BrowserRouter>
		), document.getElementById('root'));
