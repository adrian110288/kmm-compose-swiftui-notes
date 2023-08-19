//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Adrian Lesniak on 19/08/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    private var noteDataSource: NotesDataSource
    @StateObject var viewModel = NoteListViewModel(notesDataSource: nil)
    
    @State private var isNoteSelected = false
    @State private var selectedNoteId: Int64? = nil
    
    init(noteDataSource: NotesDataSource) {
        self.noteDataSource = noteDataSource
    }
    
    var body: some View {
        VStack {
            ZStack {
                NavigationLink(destination: NoteDetailScreen(noteDataSource: self.noteDataSource, noteId: selectedNoteId), isActive: $isNoteSelected) {
                    EmptyView()
                }.hidden()
                HideableSearchTextField<NoteDetailScreen>(onSearchToggled: {
                    viewModel.toggleIsSearchActive()
                }, destinationProvider: {
                    NoteDetailScreen(
                        noteDataSource: noteDataSource,
                        noteId: selectedNoteId
                    )
                }, isSearchActive: viewModel.isSearchActive, searchText: $viewModel.searchText)
                .frame(maxWidth: .infinity, minHeight: 40)
                .padding()
                
                if !viewModel.isSearchActive {
                    Text("All notes")
                        .font(.title2)
                }
            }
            
            List {
                ForEach(viewModel.filteredNotes, id: \.self.id) { note in
                    Button(action: {
                        isNoteSelected = true
                        selectedNoteId = note.id?.int64Value
                    }) {
                        NoteItem(note: note, onDeleteClick: {
                            viewModel.deleteNoteById(id: note.id?.int64Value)
                        })
                    }
                }
            }
            .onAppear {
                viewModel.loadNotes()
            }
            .listStyle(.plain)
            
        }
        .onAppear {
            viewModel.setNotesDataSource(notesDataSource: noteDataSource)
        }
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
