// Function to synchronize animations with BPM
const synchronizeAnimations = (bpm) => {
    const interval = (60 / bpm) * 1000;
    const animationElements = document.querySelectorAll('.animated-element');

    animationElements.forEach(element => {
        element.style.animationDuration = `${interval}ms`;
    });

    // Example: rotating album art synchronized with BPM
    track_art.forEach(element => {
        element.style.animation = `rotate ${interval}ms linear infinite`;
    });
}

const random_bg_color = () => {
    let hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e'];
    let a = '#';

    const populate = (a) => {
        for (let i = 0; i < 6; i++) {
            let x = Math.round(Math.random() * 14);
            let y = hex[x];
            a += y;
        }
        return a;
    };

    let Color1 = populate('#');
    let Color2 = populate('#');
    var angle = 'to right';

    let gradient = `linear-gradient(${angle}, ${Color1}, ${Color2})`;
     // document.body.style.background = gradient;
    return gradient;
}

const random_hex_color = () => {
    let hex = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e'];
    let a = '#';

    const populate = (a) => {
        for (let i = 0; i < 6; i++) {
            let x = Math.round(Math.random() * 14);
            let y = hex[x];
            a += y;
        }
        return a;
    };

    let randomHexValue = populate('#');
    //let Color2 = populate('#');
    
    let randomHexColor = randomHexValue;
    return randomHexColor;
}

const random_rgb_color = () => {
    const randomValue = () => Math.floor(Math.random() * 256);
    return `rgb(${randomValue()}, ${randomValue()}, ${randomValue()})`;
}

// Initial setup
const loadGradient = () => {
	//updateGradient();
	document.body.style.animation = 'gradient-animation 1s infinite alternate';
}

const updateGradient = () => {
  let gradient1 = random_bg_color();
  let gradient2 = random_bg_color();
  
  // Apply gradients to keyframes
  let styleElement = document.getElementById('dynamic-gradient-style');
  if (!styleElement) {
    styleElement = document.createElement('style');
    styleElement.id = 'dynamic-gradient-style';
    document.head.appendChild(styleElement);
  }

  styleElement.innerHTML = `
    @keyframes gradient-animation {
      0% {
        background: ${gradient1};
      }
      50% {
        background: ${gradient2};
      }
      100% {
        background: ${gradient1};
      }
    }
  `;
};
 /*/Initial setup
const loadGradient = () => {
	//updateGradient();
	document.body.style.animation = 'gradient-animation 10s infinite alternate';
}*/

// Change gradients every 10 seconds
//setInterval(updateGradient, 10000);
/*/const updateGradient = () => {
  let gradient1 = random_bg_color();
  let gradient2 = random_bg_color();
  
  // Apply gradients to keyframes
  let styleElement = document.getElementById('dynamic-gradient-style');
  if (!styleElement) {
    styleElement = document.createElement('style');
    styleElement.id = 'dynamic-gradient-style';
    document.head.appendChild(styleElement);
  }

  styleElement.innerHTML = `
    @keyframes gradient-animation {
      0% {
        background: ${gradient1};
      }
      50% {
        background: ${gradient2};
      }
      100% {
        background: ${gradient1};
      }
    }
  `;
};
 Initial setup
const loadGradient = () => {
	//updateGradient();
	document.body.style.animation = 'gradient-animation 10s infinite alternate';
}
*/
// Change gradients every 10 seconds
//setInterval(updateGradient, 10000);
/*const startGradientUpdate = () => {
    // Start the interval for updating gradients after a delay matching the duration of the track
    gradientUpdateTimeout = setTimeout(() => {
        updateGradient();
        // Update the gradient every 10 seconds after the initial delay
        setInterval(updateGradient, 2000);
    }, curr_track.duration * 1000); // Start after the duration of the track
}
const stopGradientUpdate = () => {
    // Clear the timeout if it exists
    clearTimeout(gradientUpdateTimeout);
    // Clear the interval if it exists
    clearInterval(gradientUpdateInterval);
}*/