import React, {useEffect} from "react";

function Chat(){
    useEffect(()=>{
        (function(d, m){
            var kommunicateSettings = 
                {"appId":"15a1e918f7eeae0799b497375abcbde76","popupWidget":true,"automaticChatOpenOnNavigation":true};
            var s = document.createElement("script"); s.type = "text/javascript"; s.async = true;
            s.src = "https://widget.kommunicate.io/v2/kommunicate.app";
            var h = document.getElementsByTagName("head")[0]; h.appendChild(s);
            window.kommunicate = m; m._globals = kommunicateSettings;
        })(document, window.kommunicate || {});
    }, []);

    return <div></div>
}

export default Chat;