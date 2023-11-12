package org.projectx.designpatterns;

public class StateManagementPattern {

    public static void main(String[] args) {
        State state = Status.NOT_STARTED;

        System.out.println(state.getCurrentState());
        System.out.println(state.getNextState());
    }

    interface State {

        State getCurrentState();

        State getNextState();
    }

    enum Status implements State {
        NOT_STARTED {
            @Override
            public State getCurrentState() {
                return NOT_STARTED;
            }

            @Override
            public State getNextState() {
                return STARTED;
            }
        }, STARTED {
            @Override
            public State getCurrentState() {
                return STARTED;
            }

            @Override
            public State getNextState() {
                return NOT_STARTED;
            }
        }, FINISHED {
            @Override
            public State getCurrentState() {
                return FINISHED;
            }

            @Override
            public State getNextState() {
                return null;
            }
        }
    }
}
