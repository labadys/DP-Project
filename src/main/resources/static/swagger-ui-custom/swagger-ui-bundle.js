const SwaggerUIBundle = {
    presets: {
        apis: function() {
            return {
                components: {},
                state: {}
            };
        }
    },
    plugins: {
        DownloadUrl: function() {
            return {
                components: {}
            };
        }
    }
};

const SwaggerUIStandalonePreset = function() {
    return {
        components: {},
        state: {}
    };
};

window.SwaggerUIBundle = SwaggerUIBundle;