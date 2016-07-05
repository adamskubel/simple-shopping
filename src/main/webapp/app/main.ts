import { bootstrap }    from '@angular/platform-browser-dynamic';

import { AppComponent } from './app.component';
import { XHRBackend } from '@angular/http';
import { HTTP_PROVIDERS } from '@angular/http';
import { APP_ROUTER_PROVIDERS } from './app.routes';

bootstrap(AppComponent, [
    APP_ROUTER_PROVIDERS,
    HTTP_PROVIDERS
]);
