import { RouterModule, Routes } from '@angular/router';
import { PartijListComponent } from './partij-list/partij-list.component';
import { HomeComponent } from './home/home.component';
import { RegeringListComponent } from './regering-list/regering-list.component';
import { PartijlidListComponent } from './partijlid-list/partijlid-list.component';
import { PartijlidCrudComponent } from './partijlid-crud/partijlid-crud.component';
import { PartijlidFormComponent } from './partijlid-form/partijlid-form.component';
import { MinisterListComponent } from './minister-list/minister-list.component';
import { MinisterCrudComponent } from './minister-crud/minister-crud.component';
import { MinisterFormComponent } from './minister-form/minister-form.component';
import { AuthGuard } from './services/auth.guard';
import { NgModule } from '@angular/core';


export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'partijen', component: PartijListComponent },
    { path: 'regeringen', component: RegeringListComponent },
    { path: 'partijleden', component: PartijlidListComponent },
    { path: 'ministers', component: MinisterListComponent },

    // Admin routes (protected by AuthGuard)
    { path: 'admin/partijleden', component: PartijlidCrudComponent, canActivate: [AuthGuard] },
    { path: 'admin/partijlid/form', component: PartijlidFormComponent, canActivate: [AuthGuard] },
    { path: 'admin/ministers', component: MinisterCrudComponent, canActivate: [AuthGuard] },
    { path: 'admin/minister/form', component: MinisterFormComponent, canActivate: [AuthGuard] },
];