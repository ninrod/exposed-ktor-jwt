import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'
import { HttpClientModule } from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'

import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { FlexLayoutModule } from '@angular/flex-layout';


import { JwtModule } from '@auth0/angular-jwt'

import { MyMaterialModule } from './material.module'

import { AppRoutingModule } from './app-routing.module'
import { AppComponent } from './app.component'
import { HomeComponent } from './pages/home/home.component'
import { LoginComponent } from './pages/login/login.component'
import { UsersService } from './service/user.service';
import { DashboardComponent } from './pages/dashboard/dashboard.component'

export function tokenRetriever() {
  let token = JSON.parse(localStorage.getItem('token'))
  if (token)
    return token.token
  return null
}

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MyMaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenRetriever,
        whitelistedDomains: [],
        blacklistedRoutes: ['/login']
      }
    })
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    DashboardComponent
  ],
  providers: [UsersService],
  bootstrap: [AppComponent]
})
export class AppModule { }
